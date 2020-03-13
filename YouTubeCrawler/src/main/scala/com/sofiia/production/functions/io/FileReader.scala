package com.sofiia.production.functions.io

import scala.io.Source.fromFile

object FileReader {

  def read(fileName: String): String ={
    val source = fromFile(fileName)
    try source.mkString finally source.close()
  }

}
