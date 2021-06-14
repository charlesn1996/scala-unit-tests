package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People


class Events_GET_json extends People {

 
  "/events" should "have the events in JSON" in {

    val event = Interset.login("j04", "j04")
    var events: Response = event
      .path("/events", Map("userId"->personList(1), "count"->"10000"))
      .get()
    events.getStatus shouldBe 200
    
    

    events = event.path("/events", Map("userId"->personList(1), "count"->"10000")).header("Accept", "application/json").get()
   
    var json = events.readEntity(classOf[JsonNode])
    //System.out.println(json.toString())
    json.findValue("dateTime").asText shouldBe "2014-11-24T15:00:00Z"
    json.findValue("userName").asText() shouldBe "archie"
    json.findValue("project").asText() shouldBe "project-1"
    json.findValue("action").asText() shouldBe "add"
    json.findValue("ip").asText() shouldBe "0.0.0.0"
    json.findValue("transaction").asText() shouldBe "(none)"
    json.findValue("clientSpec").asText() shouldBe "1"
    json.findValue("sizeKB").asText() shouldBe "0"
    
    json.findValuesAsText("dateTime").size() shouldBe 10000
    json.findValuesAsText("userName").size() shouldBe 10000
    json.findValuesAsText("project").size() shouldBe 10000
    json.findValuesAsText("action").size() shouldBe 10000
    json.findValuesAsText("ip").size() shouldBe 10000
    json.findValuesAsText("transaction").size() shouldBe 10000
    json.findValuesAsText("clientSpec").size() shouldBe 10000
    json.findValuesAsText("sizeKB").size() shouldBe 10000
  }
}