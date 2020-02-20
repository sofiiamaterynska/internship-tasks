package com.sofiia.production

object CrawlerMainLogic {

   def main(args: Array[String]) {
     val fileName=args(0)
     val APIKey=args(1)
     val response = new ApiHandler().requestOn5MostPopularVideo(APIKey)
     new FileWriterCustom().writeToFile(fileName, response.toPrettyString)
   }
}
