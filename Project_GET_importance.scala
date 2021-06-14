package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Project_GET_importance extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/projects/<projectId>/importance" should "have the ability to get a specific project importance" in {

    val project = Interset.login("j04", "j04")
    var projects: Response = project
      .path("/projects/1/importance")
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])
    json.path("value").asInt() shouldBe 1
    
  }
}