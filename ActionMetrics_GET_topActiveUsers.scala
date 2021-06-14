package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import javax.ws.rs.QueryParam


class ActionMetrics_GET_topActiveUsers extends FlatSpec with Matchers with BeforeAndAfter {

    /*
     * Login and ensure the correct list of actionMetrics topActiveUsers is displayed. 
     */

  before {
    Interset.assertTenant("J04")
  }
  //val user = login("j04", "j04")

  "/actionMetrics/topActiveUsers" should "have the default topActiveUsers actionMetrics" in {

    val actionMetric = Interset.login("j04", "j04")
    var actionMetrics: Response = actionMetric
      .path("/actionMetrics/topActiveUsers")
      .get()
    actionMetrics.getStatus shouldBe 200
    
   
    //System.out.println(actionMetrics.getLength())
    var json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("168000", "130000", "125000", "125000", "125000", "125000", "125000", "125000", "125000", "124500")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "4", "10", "9", "8", "7", "6", "2", "3", "5")
    json.findValuesAsText("projectId").toArray() shouldBe Array("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1")
    
    json.findValuesAsText("actionsCount").size() shouldBe 10
    json.findValuesAsText("userId").size() shouldBe 10
    json.findValuesAsText("projectId").size() shouldBe 10
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveUsers", Map("projectId"->"1")).get()
    actionMetrics.getStatus shouldBe 200

    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("142560", "8500", "7500", "2500", "500", "500", "500", "500", "500", "500")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "12", "3", "2", "10", "8", "7", "9", "5", "6")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "1", "1", "1", "1", "1", "1", "1", "1", "1")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveUsers", Map("count"->"3")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("168000", "130000", "125000")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "4", "3")
    json.findValuesAsText("projectId").toArray() shouldBe Array("-1", "-1", "-1")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveUsers", Map("projectId"->"1", "count"->"3")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("142560", "8500", "7500")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "12", "3")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "1", "1")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveUsers", Map("projectId"->"99", "count"->"10")).get()
    actionMetrics.getStatus shouldBe 200
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveUsers", Map("count"->"0")).get()
    actionMetrics.getStatus shouldBe 500
    
  }
}
