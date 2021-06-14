package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_bots extends People {


  "/people/bots" should "get bot users" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/bots")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    val userId = json.findValuesAsText("userId").toArray()
    val score = json.findValuesAsText("score").toArray()
    
    userId should contain (personList(0))
    userId should contain (personList(10))
    
    val x = userId.indexOf(personList(0))
    val y = userId.indexOf(personList(10))
    
    score(x) shouldBe "100.0"
    score(y) shouldBe "99.63"
    
    
    json.findValuesAsText("userId").size() shouldBe 2
    json.findValuesAsText("score").size() shouldBe 2
    
  }
}