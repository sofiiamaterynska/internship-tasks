package com.sofiia.production

import com.sofiia.production.functions.io.{FileReader, FileWriterCustom}
import com.sofiia.production.functions.{JSONHandler, YouTubeCrawler}

object Main {

   def main(args: Array[String]) {
     val fileName=args(0)
     val APIKey=args(1)
     val response = new YouTubeCrawler().requestOn5MostPopularVideo(APIKey)
     new FileWriterCustom().writeToFile(fileName, response.toPrettyString)
     new JSONHandler().jsonHandling(new FileReader().readFromFile(fileName))
   }
}
