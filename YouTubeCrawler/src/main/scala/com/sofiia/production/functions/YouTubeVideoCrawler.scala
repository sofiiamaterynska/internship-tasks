package com.sofiia.production.functions

import com.google.api.services.youtube.model.{Video, VideoListResponse}
import com.sofiia.production.functions.YouTubeCrawler.Region.Region

class YouTubeVideoCrawler {
  val YTCrawler = new YouTubeCrawler()

  def requestOnMostPopularVideo(APIKey: String, numberOfResults: Long, region: Region):  VideoListResponse={
    val request = YTCrawler.connection(APIKey).videos.list("snippet,contentDetails,statistics").setMaxResults(numberOfResults)
    request.setChart("mostPopular").setRegionCode(region.toString).execute
  }

  def getVideoDataById(APIKey:String, Id: String): Video = {
    val videoResponse = YTCrawler.connection(APIKey).videos().list("snippet,contentDetails,statistics").setId(Id).execute()
    videoResponse.getItems.get(0)

  }
}
