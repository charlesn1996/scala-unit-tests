package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId_workingHours_daily extends People {


  "/people/<personId>/workingHours/daily" should "have the ability to get user daily working hours" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(0) + "/workingHours/daily")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.findValue("minute").asText() shouldBe "1"
    json.findValue("expected").asText() shouldBe "1.013928831496068"
    
    json.findValuesAsText("minute").size() shouldBe 1440
    json.findValuesAsText("expected").size() shouldBe 1440
    
    persons = person.path("/people/" + personList(10) + "/workingHours/daily").get()
    
    json = persons.readEntity(classOf[JsonNode])
    json.findValue("minute").asText() shouldBe "1"
    json.findValue("expected").asText() shouldBe "0.9952784674274092"
    
    json.findValuesAsText("minute").size() shouldBe 1440
    json.findValuesAsText("expected").size() shouldBe 1440
    
  }
}