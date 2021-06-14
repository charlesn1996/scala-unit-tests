package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class People_GET_risk extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/people/risk" should "have the ability to get organizational risk" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/risk")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.findValue("entityId").asText() shouldBe "0"
    json.findValue("time").asText() shouldBe "2014-11-24T05:00:00Z"
    json.findValue("value").asInt() shouldBe 0
    
  }
}