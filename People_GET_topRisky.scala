package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class People_GET_topRisky extends People {


  "/people/risk" should "have the ability to get organizational risk" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/topRisky", Map("count"->"10"))
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.findValuesAsText("entityId").toArray() shouldBe Array(personList(2), personList(1), personList(5), personList(11), personList(4), personList(3),personList(6), personList(7), personList(8), personList(0))
    json.findValuesAsText("value").toString() shouldBe "[22.0, 8.0, 6.0, 3.0, 3.0, 1.0, 1.0, 1.0, 1.0, 0.0]"
    
    json.findValuesAsText("entityId").size() shouldBe 10
    json.findValuesAsText("value").size() shouldBe 10
    
    persons = person.path("/people/topRisky", Map("count"->"0")).get() // Outputs same result as when the count = 10. Needs fix
    persons.getStatus shouldBe 200
    
  }
}