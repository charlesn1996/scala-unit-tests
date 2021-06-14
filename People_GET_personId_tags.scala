package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId_tags extends People {


  "/people/<personId>/tags" should "have specific user tags" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(0) + "/tags")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])

    json.findValue("entityType").asText() shouldBe "usr"
    json.findValue("entityId").asText shouldBe personList(0)
    json.findValue("tag").asText() shouldBe "bot"
    
    persons = person.path("/people/11/tags").get()
    persons.getStatus shouldBe 200
    
    json = persons.readEntity(classOf[JsonNode])
    json.findValue("entityType").asText shouldBe "usr"
    json.findValue("entityId").asText() shouldBe personList(10)
    json.findValue("tag").asText() shouldBe "bot"
    
    persons = person.path("/people/" + personList(11) + "/tags").get()
    persons.getStatus shouldBe 200
    
    json = persons.readEntity(classOf[JsonNode])
    json.findValuesAsText("entityType").size() shouldBe 0
    json.findValuesAsText("entityId").size() shouldBe 0
    json.findValuesAsText("tag").size() shouldBe 0

  }
}