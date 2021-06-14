package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId_workingHours_weekly extends People {
  

  "/people/<personId>/workingHours/weekly" should "have the ability to get user weekly working hours" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(0) + "/workingHours/weekly")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.findValue("minute").asText() shouldBe "1"
    json.findValue("expected").asText() shouldBe "1.0000008414400576"
    
    json.findValuesAsText("minute").size() shouldBe 10080
    json.findValuesAsText("expected").size() shouldBe 10080
    
    persons = person.path("/people/" + personList(10) + "/workingHours/weekly").get()
    
    json = persons.readEntity(classOf[JsonNode])
    json.findValue("minute").asText() shouldBe "1"
    json.findValue("expected").asText() shouldBe "2.0712624548743923"
    
    json.findValuesAsText("minute").size() shouldBe 10080
    json.findValuesAsText("expected").size() shouldBe 10080
    
  }
}