package com.sofiia.production

import java.io.{BufferedWriter, File, FileWriter}

class FileWriterCustom {

  def writeToFile(fileName: String, textToWrite: String): Unit ={
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(textToWrite)
    bw.close()
  }

}
