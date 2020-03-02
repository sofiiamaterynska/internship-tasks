package com.sofiia.production

import java.util.concurrent.Executors

import com.sofiia.production.functions.io.{FileReader, FileWriterCustom}
import com.sofiia.production.functions.{YTVideoListParser, YouTubeVideoCrawler}
import com.google.api.services.youtube.model.VideoListResponse
import com.sofiia.production.functions.YouTubeCrawler.Region

object Main {
  val videosIDArray=Array("wkPR4Rcf4ww", "WNeLUngb-Xg", "rR4n-0KYeKQ", "8kooIgKESYE", "_Yhyp-_hX2s" )

   def main(args: Array[String]) {
     val properties: Properties = new Properties()
     properties.load(new FileInputStream(args(0)))

     val fileLocation=properties.getProperty("videoByIdDirectory")
     val mostPopularVideosFileName = properties.getProperty("mostPopularVideoFile")
     val APIKey = properties.getProperty("APIKey")
     val amountOfResults = properties.getProperty("numberOfVideos")
     val fileNameForFuture = properties.getProperty("fileNameForFuture")

     val response: VideoListResponse = new YouTubeVideoCrawler()
       .requestOnMostPopularVideo(APIKey, amountOfResults.toInt, Region.UnitedStates)
     FileWriterCustom.write(mostPopularVideosFileName, response.toPrettyString)
     new YTVideoListParser().fromJson(FileReader.read(mostPopularVideosFileName))

     parallelDataCrawlingToFiles(APIKey, YTVideoJson.fileLocation)
   }

  def parallelDataCrawlingToFiles(APIKey: String, fileLocation: String) : Unit = {
    val pool = Executors.newFixedThreadPool(2)

    videosIDArray.foreach { item =>
      pool.execute(
        new Runnable {
          def run {
            val video = new YouTubeVideoCrawler().getVideoDataById(APIKey, item)
            FileWriterCustom
              .write(fileLocation + videosIDArray.indexOf(item) + ".json", video.toPrettyString)
          }
        }
      )
    }
    pool.shutdown()
  }
}
