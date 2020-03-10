package com.sofiia.production.akka

import akka.actor.Actor
import com.sofiia.production.akka.StorageActor.Operations
import com.sofiia.production.functions.io.FileWriterCustom

import scala.collection.mutable.ArrayBuffer

class StorageActor(array: ArrayBuffer[String], expectedSize: Int,  filePathForAkka: String) extends Actor {
  override def receive: Receive = {
    case (Operations.ADD, videoJSON: String) =>
      array.addOne(videoJSON)
    case Operations.FLUSH =>
      FileWriterCustom.writeFromListToJson(filePathForAkka, array.toList)
      println(array)
      array.clear()
    case _ =>
      println("Wrong message sent to storage actor")
  }
}

object StorageActor {
  object Operations extends Enumeration {
    val ADD, FLUSH = Value
  }
}
