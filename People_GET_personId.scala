package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_personId extends People {
  

  "/people/<personId>" should "have the specific person listed" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/" + personList(3)) // doesn't change regardless of the personId entered. **Needs fix**
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])

    json.path("id").asText() shouldBe personList(3)
    json.path("name").asText() shouldBe "danielle"
    
  }
}