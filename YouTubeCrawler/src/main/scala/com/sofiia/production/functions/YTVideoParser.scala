package com.sofiia.production.functions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.JsonNode
import com.sofiia.production.DOM.{JSONKeyArrays, YTVideo}

class YTVideoParser{

  def jsonParsing(JSONString: String): List[YTVideo] ={
    val mapper = new ObjectMapper()
    val root: JsonNode = mapper.readTree(JSONString)
    val array = mapper.convertValue[Array[JsonNode]](root.get("items"),classOf[Array[JsonNode]])
    array.map(x=>YTVideo(getNestedValueLvl2(x, JSONKeyArrays.title),
      getNestedValueLvl2(x, JSONKeyArrays.likes).toInt,
      getNestedValueLvl2(x, JSONKeyArrays.dislikes).toInt,
      getNestedValueLvl2(x, JSONKeyArrays.views).toInt,
      getNestedValueLvl2(x, JSONKeyArrays.chanelTitle)
    )).toList
  }

  def getNestedValueLvl2(tree: JsonNode, array: Array[String]): String ={
    tree.get(array(0)).get(array(1)).asText()
  }
}
