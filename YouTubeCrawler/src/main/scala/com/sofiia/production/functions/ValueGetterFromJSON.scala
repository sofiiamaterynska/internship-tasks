package com.sofiia.production.functions

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

class ValueGetterFromJSON {

  def getValueFromNestedJSON(item: Any, key: Array[String], iterationsLeft: Int): Any = {
    if(iterationsLeft>0)
      getValueFromNestedJSON(item.asInstanceOf[Map[String, Any]](key(key.length-iterationsLeft)), key, iterationsLeft-1)
    else
      item
  }

  def getListFromJSONString(JSONString: String, key: String): List[Any] ={
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    val valueOfJSON=mapper.readValue(JSONString, classOf[Map[String, Any]])
    valueOfJSON(key).asInstanceOf[List[Any]]
  }
}
