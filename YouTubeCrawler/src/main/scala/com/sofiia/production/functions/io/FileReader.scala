package com.sofiia.production.functions.io

import scala.io.Source._

class FileReader {

  def readFromFile(fileName: String): String ={
    val source = fromFile(fileName)
    try source.mkString finally source.close()
  }

}
