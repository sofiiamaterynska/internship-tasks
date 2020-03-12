package com.sofiia.production.functions

import com.google.api.client.http.{HttpRequest, HttpRequestInitializer}
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.model.{Video, VideoListResponse}
import com.google.api.services.youtube.{YouTube, YouTubeRequestInitializer}
import com.sofiia.production.functions.YouTubeCrawler.Region.Region

object YouTubeCrawler{
  object Region extends Enumeration{
    type Region = Value
    val UnitedStates=Value("US")
    val Germany=Value("DE")
    val GreatBritain=Value("GB")
    val Spain=Value("ES")
    val FaroeIslands=Value("FO")
    val Canada=Value("CA")
    val Ukraine= Value("UA")
  }
}

class YouTubeCrawler{

  // init google api access
  val transport = new NetHttpTransport()
  val factory = new JacksonFactory()
  val httpRequestInit = new HttpRequestInitializer {
    override def initialize(re: HttpRequest): Unit = {}
  }

  def connection(APIKey: String): YouTube ={
    new YouTube.Builder(transport, factory, httpRequestInit).setApplicationName("test")
      .setYouTubeRequestInitializer(new YouTubeRequestInitializer(APIKey)).build()
  }

}
