package com.sofiia.production

import com.sofiia.production.functions.io.{FileReader, FileWriterCustom}
import com.sofiia.production.functions.{YTVideoParser, YouTubeCrawler}
import com.google.api.services.youtube.model.VideoListResponse
import com.sofiia.production.functions.YouTubeCrawler.Region

object Main {

   def main(args: Array[String]) {
     val fileName=args(0)
     val APIKey=args(1)
     val amountOfResults=args(2)
     val response : VideoListResponse = new YouTubeCrawler()
       .requestOnMostPopularVideo(APIKey, amountOfResults.toInt, Region.UnitedStates.toString)
     new FileWriterCustom().writeToFile(fileName, response.toPrettyString)
     new YTVideoParser().jsonParsing(new FileReader().readFromFile(fileName))
   }
}
