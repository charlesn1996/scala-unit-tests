package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Projects

class Project_GET_risk extends Projects {
  

  "/projects/<projectId>/risk" should "have the ability to get a specific project risk" in {

    val project = Interset.login("j04", "j04")
    var projects: Response = project
      .path("/projects/" + projectList(19) + "/risk") 
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])
    json.findValuesAsText("entityId").toArray shouldBe Array(projectList(19), projectList(19), projectList(19), projectList(19), projectList(19), projectList(19))
    json.findValuesAsText("time").toArray shouldBe Array("2014-12-12T15:00:00Z", "2014-12-12T16:00:00Z", "2014-12-12T17:00:00Z", "2014-12-12T18:00:00Z", "2014-12-12T19:00:00Z", "2014-12-12T20:00:00Z")
    json.findValuesAsText("value").toArray shouldBe Array("0.466540020918174", "0.9284146416271664", "0.9191304952108946", "1.3764792111769597", "1.829254439983364", "2.2775019165017043")
    
    json.findValuesAsText("entityId").size shouldBe 6
    json.findValuesAsText("time").size shouldBe 6
    json.findValuesAsText("value").size shouldBe 6
    
  }
}