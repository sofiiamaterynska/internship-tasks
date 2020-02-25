package com.sofiia.production.functions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.JsonNode
import com.sofiia.production.DOM.{YTVideoJson, YTVideo}

class YTVideoListParser{

  def fromJson(YTVideoJSONString: String): List[YTVideo] ={

    def getNestedValueLvl2(tree: JsonNode, array: Array[String]): String ={
      tree.get(array(0)).get(array(1)).asText()
    }

    val mapper = new ObjectMapper()
    val root: JsonNode = mapper.readTree(YTVideoJSONString)
    val array = mapper.convertValue[Array[JsonNode]](root.get("items"),classOf[Array[JsonNode]])
    array.map(x=>YTVideo(getNestedValueLvl2(x, YTVideoJson.title),
      getNestedValueLvl2(x, YTVideoJson.likes).toInt,
      getNestedValueLvl2(x, YTVideoJson.dislikes).toInt,
      getNestedValueLvl2(x, YTVideoJson.views).toInt,
      getNestedValueLvl2(x, YTVideoJson.chanelTitle)
    )).toList
  }
}
