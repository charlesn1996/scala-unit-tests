package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Models

class Models_GET_projectCommunities extends Models {

  
  "/models/projectCommunities" should "have the correct project communities" in {

    val model = Interset.login("j04", "j04")
    var models: Response = model
      .path("/models/projectCommunities")
      .get()
    models.getStatus shouldBe 200
    
   
    var json = models.readEntity(classOf[JsonNode])
    json.findValue("id").asText() shouldBe modelList1(0)
    json.findValue("communityId").asText() shouldBe modelList1(0)
    json.findValue("weight").asText() shouldBe "1"
    
    json.findValuesAsText("id").size() shouldBe 20
    json.findValuesAsText("communityId").size() shouldBe 20
    json.findValuesAsText("weight").size() shouldBe 20
    
  }
}