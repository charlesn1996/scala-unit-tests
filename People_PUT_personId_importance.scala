package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Importance

class People_PUT_personId_importance extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/people/<personId>/importance" should "have the ability to set a specific person importance" in {

    val person = Interset.login("j04", "j04")
    var persons: Response = person
      .path("/people/3/importance")
      .get()
    persons.getStatus shouldBe 200
    
   
    var json = persons.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 1
    
    val t = new Importance()
    t.value = 5
    
    persons = person.path("/people/3/importance").put(Entity.json(t))
    persons.getStatus shouldBe 200
    
    json = persons.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 5
    
    val s = new Importance()
    s.value = 1
    
    persons = person.path("/people/3/importance").put(Entity.json(s))
    persons.getStatus shouldBe 200
    
    json = persons.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 1
    
  }
}