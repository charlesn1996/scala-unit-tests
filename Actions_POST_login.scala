package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Action

class Actions_POST_login extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/actions/login" should "confirm login with valid credentials" in {

    val action = Interset.login("j04", "j04")
//    var actions: Response = action
//      .path("/actions/login")
//      .put(Entity.json())
//    actions.getStatus shouldBe 200
    
    var t = new Action()
    t.username = "admin"
    t.password = "password"
    
    var actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 200
    
    var json = actions.readEntity(classOf[JsonNode])
    json.path("token_type").asText shouldBe "Bearer"
    
    t.username = "user"
    t.password = "password"
    
    actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 200
    
    json = actions.readEntity(classOf[JsonNode])
    json.path("token_type").asText shouldBe "Bearer"
    
    t.username = "root"
    t.password = "root"
    
    actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 200
    
    json = actions.readEntity(classOf[JsonNode])
    json.path("token_type").asText shouldBe "Bearer"
    
    // Checking for 401 response in non-existent users or wrong passwords
    t.username = "user"
    t.password = "passwordABC"
    
    actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 401
    
    
    t.username = "userABC"
    t.password = "password"
    
    actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 401
    
    
    t.username = "paul"
    t.password = "paul"
    
    actions = action.path("/actions/login").post(Entity.json(t))
    actions.getStatus shouldBe 401
    
  }
}