package com.sofiia.production

import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.{Callable, Executors, FutureTask}

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

     parallelDataCrawlingToFiles(APIKey, fileLocation)
     parallelDataCrawlingWithFutureToFile(APIKey, fileNameForFuture)
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

  def parallelDataCrawlingWithFutureToFile(APIKey: String, fileLocation: String) : Unit = {
    val pool = Executors.newFixedThreadPool(5)

    val newList = videosIDArray.map { item =>
      val future = new FutureTask[String] (new Callable[String] {
      def call: String = {
        val video = new YouTubeVideoCrawler().getVideoDataById(APIKey, item)
        video.toPrettyString
      }
    })
      pool.execute(future)
      future.get()
    }.toList

    pool.shutdown()
    FileWriterCustom.writeFromListToJson(fileLocation, newList)
  }
}
