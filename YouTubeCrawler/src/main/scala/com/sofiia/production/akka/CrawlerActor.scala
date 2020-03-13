package com.sofiia.production.akka

import akka.actor.{Actor, ActorRef}
import com.sofiia.production.akka.StorageActor.Operations
import com.sofiia.production.functions.YouTubeVideoCrawler

class CrawlerActor(APIKey: String,  actorRef: ActorRef) extends Actor{
  override def receive: Receive = {
    case id: String =>
      val video = new YouTubeVideoCrawler().getVideoDataById(APIKey, id)
      actorRef ! (Operations.ADD, video.toPrettyString)
    case _ => println("Wrong message sent to worker actor")
  }
}
