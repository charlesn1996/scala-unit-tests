package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId_behaviors extends People {

  
  "/people/<personId>/behaviours" should "have the specific person listed" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(2) + "/behaviors")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    
    json.findValue("userName").asText() shouldBe "camilla"
    val x = json.findValue("risk").asDouble()*100 
    x.toInt shouldBe 6
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asText() shouldBe "1"
    json.findValue("userId").asText() shouldBe personList(2)
    json.findValue("projectId").asText() shouldBe "-1"
    json.findValue("projectName").asText() shouldBe "(none)"
    json.findValue("text").asText() shouldBe "persistent anomaly"
    json.findValue("category").asText() shouldBe "null"
    json.findValue("sneakingScore").asText() shouldBe "0"
    json.findValue("wanderingScore").asText() shouldBe "0"
    json.findValue("moochingScore").asText() shouldBe "0"
    json.findValue("hoardingScore").asText() shouldBe "0"
    
  }

}