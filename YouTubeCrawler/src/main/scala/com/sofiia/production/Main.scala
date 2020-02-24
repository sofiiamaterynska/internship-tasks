package com.sofiia.production

import com.sofiia.production.functions.{FileWriterCustom, JSONReader, YouTubeCrawler}

object CrawlerMainLogic {

   def main(args: Array[String]) {
     val fileName=args(0)
     val APIKey=args(1)
     val response = new YouTubeCrawler().requestOn5MostPopularVideo(APIKey)
     new FileWriterCustom().writeToFile(fileName, response.toPrettyString)
     println(response.toPrettyString)
     new JSONReader().jsonHandling(response.toString)
   }
}
