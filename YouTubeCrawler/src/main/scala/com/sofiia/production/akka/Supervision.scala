package com.sofiia.production.akka

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.BalancingPool
import com.sofiia.production.akka.StorageActor.Operations

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class Supervision (implicit executionContext: ExecutionContext) {
  def parallelAkka(APIKey: String, idArray: Array[String], numberOfActors: Int, filePathForAkka: String) = {
    val system = ActorSystem("YT-Crawler")
    val array: ArrayBuffer[String] = ArrayBuffer.empty[String]
    val writer = system.actorOf(Props(new StorageActor(array, idArray.length,  filePathForAkka)), name = "writer")
    val router2: ActorRef =
      system.actorOf(BalancingPool(numberOfActors).props(Props(new Workers(APIKey,writer))), "router2")
    idArray.foreach(item => router2 ! item )
    system.scheduler.scheduleWithFixedDelay(0.microseconds, 5.seconds, writer, Operations.FLUSH)
  }
}
