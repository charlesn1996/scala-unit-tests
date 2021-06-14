package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.Models

class Models_GET_userCommunities extends Models {

  
  "/models/userCommunities" should "have the correct user communities" in {

    val model = Interset.login("j04", "j04")
    var models: Response = model
      .path("/models/userCommunities")
      .get()
    models.getStatus shouldBe 200
    
   
    var json = models.readEntity(classOf[JsonNode])
    json.findValue("id").asText() shouldBe modelList2(0)
    json.findValue("communityId").asText() shouldBe modelList2(0)
    json.findValue("weight").asText() shouldBe "1"
    
    json.findValuesAsText("id").size() shouldBe 12
    json.findValuesAsText("communityId").size() shouldBe 12
    json.findValuesAsText("weight").size() shouldBe 12
    
  }
}