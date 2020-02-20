package com.sofiia.production

import com.google.api.client.http.{HttpRequest, HttpRequestInitializer}
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.model.VideoListResponse
import com.google.api.services.youtube.{YouTube, YouTubeRequestInitializer}

class ApiHandler {

  // init google api access
  val transport = new NetHttpTransport()
  val factory = new JacksonFactory()
  val httpRequestInit = new HttpRequestInitializer {
    override def initialize(re: HttpRequest): Unit = {}
  }

  def requestOn5MostPopularVideo(APIKey: String) : VideoListResponse ={
    val service = new YouTube.Builder(transport, factory, httpRequestInit).setApplicationName("test")
      .setYouTubeRequestInitializer(new YouTubeRequestInitializer(APIKey))
      .build() //connection()
    val request = service.videos.list("snippet,contentDetails,statistics")
    request.setChart("mostPopular").setRegionCode("US").execute
  }
}
