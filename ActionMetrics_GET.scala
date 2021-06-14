package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class ActionMetrics_GET extends FlatSpec with Matchers with BeforeAndAfter {

    /*
     * Login and ensure the correct list of actionMetrics topActiveUsers is displayed. 
     */

  before {
    Interset.assertTenant("J04")
  }
  //val user = login("j04", "j04")

  "/actionMetrics" should "have the default actionMetrics" in {

    val actionMetric = Interset.login("j04", "j04")
    var actionMetrics: Response = actionMetric
      .path("/actionMetrics")
      .get()
    actionMetrics.getStatus shouldBe 200
    
   
    var json = actionMetrics.readEntity(classOf[JsonNode])
    
    json.path("actionsCount").asText() shouldBe "1315600"
    json.path("userId").asText() shouldBe "-1"
    json.path("projectId").asText() shouldBe "-1"   

    
    actionMetrics = actionMetric.path("/actionMetrics", Map("userId"->"1")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.path("actionsCount").asText() shouldBe "168000"
    json.path("userId").asText() shouldBe "1"
    json.path("projectId").asText() shouldBe "-1"   
    
    actionMetrics = actionMetric.path("/actionMetrics", Map("projectId"->"1")).get()
    actionMetrics.getStatus shouldBe 200
   
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.path("actionsCount").asText() shouldBe "164060"
    json.path("userId").asText() shouldBe "-1"
    json.path("projectId").asText() shouldBe "1"
   
    actionMetrics = actionMetric.path("/actionMetrics", Map("userId"->"1", "projectId"->"1")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.path("actionsCount").asText() shouldBe "142560"
    json.path("userId").asText() shouldBe "1"
    json.path("projectId").asText() shouldBe "1"
    
    actionMetrics = actionMetric.path("/actionMetrics", Map("userId"->"99")).get()
    actionMetrics.getStatus shouldBe 204  
    
    actionMetrics = actionMetric.path("/actionMetrics", Map("projectId"->"99")).get()
    actionMetrics.getStatus shouldBe 204    
    
    actionMetrics = actionMetric.path("/actionMetrics", Map("userId"->"99", "projectId"->"99")).get()
    actionMetrics.getStatus shouldBe 204    
    
  }
}
