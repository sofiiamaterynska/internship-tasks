package com.sofiia.production.DOM

case class YTVideo (title: String, likes: Int, dislikes: Int, views: Int, channelTitle: String){
  override def toString(): String = {
    "\ntitle: " + title + "\nchannel title: " + channelTitle + "\nlikes: " +
      likes + "\ndislikes: " + dislikes + "\nviews: " + views
  }
}
