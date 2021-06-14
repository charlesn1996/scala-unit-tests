package com.interset.test.J04

import com.interset.test.Interset
import com.interset.test.Projects
import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}

class Project_GET_projectId extends Projects {

//  before {
//    Interset.assertTenant("J04")
//  }
  

  "/projects/<projectId>" should "have the ability to get a specific project" in {

    val project = Interset.login("j04", "j04")
    var projects: Response = project
      .path("/projects/" + projectList.apply(0))
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])

    json.path("id").asText() shouldBe projectList.apply(0)  //Another bug. Always outputs this project regardless of projectId input
    json.path("name").asText() shouldBe "project-2"
    
  }
}