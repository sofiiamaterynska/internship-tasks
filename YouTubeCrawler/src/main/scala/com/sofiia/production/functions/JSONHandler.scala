package com.sofiia.production.functions

import com.sofiia.production.DOM.{JSONKeyArrays, YTVideo}


class JSONHandler{

  def jsonHandling(JSONString: String): Set[YTVideo] ={
    val getter=new ValueGetterFromJSON()
    val listOfVideoData  = getter.getListFromJSONString(JSONString, "items")
    var videoSet: Set[YTVideo] = Set()
    for(list<-listOfVideoData){
      videoSet+=YTVideo(
        getter.getValueFromNestedJSON(list,JSONKeyArrays.title, JSONKeyArrays.title.length).toString,
        getter.getValueFromNestedJSON(list,JSONKeyArrays.likes, JSONKeyArrays.likes.length).toString.toInt,
        getter.getValueFromNestedJSON(list,JSONKeyArrays.dislikes, JSONKeyArrays.dislikes.length).toString.toInt,
        getter.getValueFromNestedJSON(list,JSONKeyArrays.views, JSONKeyArrays.views.length).toString.toInt,
        getter.getValueFromNestedJSON(list,JSONKeyArrays.chanelTitle, JSONKeyArrays.chanelTitle.length).toString
      )
    }
    videoSet
  }
}
