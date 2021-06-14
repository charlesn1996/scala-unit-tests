package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Projects_GET extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/projects" should "get all projects" in {

    val project = Interset.login("j04", "j04")
    var projects: Response = project
      .path("/projects")
      .get()
    projects.getStatus shouldBe 200
    
   
    var json = projects.readEntity(classOf[JsonNode])

    //json.findValuesAsText("id").toArray() shouldBe Array("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20")
    val projectNames = json.findValuesAsText("name").toArray() 
    projectNames should contain ("project-1")
    projectNames should contain ("project-2")
    projectNames should contain ("project-3")
    projectNames should contain ("project-4")
    projectNames should contain ("project-5")
    projectNames should contain ("project-6")
    projectNames should contain ("project-7")
    projectNames should contain ("project-8")
    projectNames should contain ("project-9")
    projectNames should contain ("project-10")
    projectNames should contain ("project-11")
    projectNames should contain ("project-12")
    projectNames should contain ("project-13")
    projectNames should contain ("project-14")
    projectNames should contain ("project-15")
    projectNames should contain ("project-16")
    projectNames should contain ("project-17")
    projectNames should contain ("project-18")
    projectNames should contain ("project-19")
    projectNames should contain ("project-20")
    
    
    json.findValuesAsText("id").size() shouldBe 20
    json.findValuesAsText("name").size() shouldBe 20
    
  }
}