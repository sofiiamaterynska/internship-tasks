package com.sofiia.production.functions.io

import java.io.{BufferedWriter, File, FileWriter}

object FileWriterCustom {

  def write(fileName: String, textToWrite: String): Unit ={
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(textToWrite)
    bw.close()
  }

  def writeFromListToJson(fileName: String, list:List[String]): Unit ={
    write(fileName, list.toString.replace("List(","[").replace(')', ']'))
  }

}
