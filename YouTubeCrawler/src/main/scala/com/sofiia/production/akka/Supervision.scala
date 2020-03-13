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
    val writer = system.actorOf(Props(new StorageActor(idArray.length,  filePathForAkka)), name = "writer")
    val poolOfCrawlerActors: ActorRef =
      system.actorOf(BalancingPool(numberOfActors).props(Props(new CrawlerActor(APIKey,writer))), "router")
    idArray.foreach(item => poolOfCrawlerActors ! item )
    system.scheduler.scheduleWithFixedDelay(0.microseconds, 5.seconds, writer, Operations.FLUSH)
    Thread.sleep(7000)
    system.terminate()
  }
}
