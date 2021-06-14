package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId_risk extends People {


  "/people/<personId>/risk" should "have the ability to get a specific user risk" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(0) + "/risk")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.findValue("entityId").asText() shouldBe personList(0)
    json.findValue("time").asText() shouldBe "2014-11-24T05:00:00Z"
    json.findValue("value").asInt() shouldBe 0
    
  }
}