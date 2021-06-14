package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Projects

class Project_GET_topRisky extends Projects {


  "/projects/topRisky" should "have the ability to get the top 10 risky projects" in {

    val project = Interset.login("j04", "j04")
    var projects: Response = project
      .path("/projects/topRisky", Map("count"->"10"))
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])
    json.findValuesAsText("entityId").toArray shouldBe Array(projectList(13), projectList(14), projectList(15), projectList(16), projectList(17), projectList(0), projectList(7), projectList(2), projectList(9), projectList(10))
    json.findValuesAsText("value").toString shouldBe "[19.0, 19.0, 19.0, 19.0, 19.0, 7.000000000000001, 7.000000000000001, 6.0, 5.0, 5.0]"
    
    json.findValuesAsText("entityId").size() shouldBe 10
    json.findValuesAsText("value").size() shouldBe 10
    
    // On Cortex, changing the count to 0 outputs 10 items. Probably a bug
    projects = project.path("/projects/topRisky", Map("count"->"0")).get()
    projects.getStatus shouldBe 200
    
  }
}