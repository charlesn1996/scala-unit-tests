package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Project


class Project_PUT_importance extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/projects/<projectId>/importance" should "have the ability to set a specific project importance" in {

    val project = Interset.login("admin", "password")
    var projects: Response = project
      .path("/projects/1/importance")
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 1
    
    val t = new Project()
    t.value = 9.99
    
    projects = project.path("/projects/1/importance").put(Entity.json(t))
    projects.getStatus shouldBe 200
    
    projects = project.path("/projects/1/importance").get()
    json = projects.readEntity(classOf[JsonNode])
    json.path("value").asDouble() shouldBe 9.99
    
    val s = new Project()
    s.value = 1
    
    projects = project.path("/projects/1/importance").put(Entity.json(s))
    projects.getStatus shouldBe 200
    
    projects = project.path("/projects/1/importance").get()
    json = projects.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 1
    
  }
}