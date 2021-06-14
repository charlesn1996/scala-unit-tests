package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET extends People {


  "/people/" should "have the default list of people" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])

    json.findValuesAsText("id").toArray() shouldBe personList
    json.findValuesAsText("name").toString() shouldBe "[bob, archie, camilla, danielle, erin, heidi, ilsa, jay, kyle, lewis, gustav, frank]"
    
    json.findValuesAsText("id").size() shouldBe 12
    json.findValuesAsText("name").size() shouldBe 12
  }
}