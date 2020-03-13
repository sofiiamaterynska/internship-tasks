package com.sofiia.production.akka

import akka.actor.Actor
import com.sofiia.production.akka.StorageActor.Operations
import com.sofiia.production.functions.io.FileWriterCustom

import scala.collection.mutable.ArrayBuffer

object StorageActor {
  object Operations extends Enumeration {
    val ADD, FLUSH = Value
  }
}

class StorageActor(expectedSize: Int,  filePathForAkka: String) extends Actor {
  val array: ArrayBuffer[String] = ArrayBuffer.empty[String]
  override def receive: Receive = {
    case (Operations.ADD, videoJSON: String) =>
      array.addOne(videoJSON)
    case Operations.FLUSH =>
      FileWriterCustom.writeFromListToJson(filePathForAkka, array.toList)
      println(array.toString())
      array.clear()
    case _ =>
      println("Wrong message sent to storage actor")
  }
}
