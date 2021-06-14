package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class ActionMetrics_GET_topActiveProjects extends FlatSpec with Matchers with BeforeAndAfter {

    /*
     * Login and ensure the correct list of actionMetrics topActiveProjects is displayed. 
     */

  before {
    Interset.assertTenant("J04")
  }
  //val user = login("j04", "j04")

  "/actionMetrics/topActiveProjects" should "have the default topActiveProjects actionMetrics" in {

    val actionMetric = Interset.login("j04", "j04")
    var actionMetrics: Response = actionMetric
      .path("/actionMetrics/topActiveProjects")
      .get()
    actionMetrics.getStatus shouldBe 200
    
   
    //System.out.println(actionMetrics.getLength())
    var json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("164060", "131100", "130750", "130750", "130750", "130000", "127100", "126750", "118250", "23850")
    json.findValuesAsText("userId").toArray() shouldBe Array("-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "5", "9", "10", "11", "3", "4", "12", "8", "7")
    actionMetrics.getStatus shouldBe 200
    
    json.findValuesAsText("actionsCount").size() shouldBe 10
    json.findValuesAsText("userId").size() shouldBe 10
    json.findValuesAsText("projectId").size() shouldBe 10
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveProjects", Map("userId"->"1")).get()
    actionMetrics.getStatus shouldBe 200

    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("142560", "15840", "9600")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "1", "1")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "2", "4")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveProjects", Map("count"->"2")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("164060", "131100")
    json.findValuesAsText("userId").toArray() shouldBe Array("-1", "-1")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "5")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveProjects", Map("userId"->"1", "count"->"2")).get()
    actionMetrics.getStatus shouldBe 200
    
    json = actionMetrics.readEntity(classOf[JsonNode])
    json.findValuesAsText("actionsCount").toArray() shouldBe Array("142560", "15840")
    json.findValuesAsText("userId").toArray() shouldBe Array("1", "1")
    json.findValuesAsText("projectId").toArray() shouldBe Array("1", "2")
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveProjects", Map("projectId"->"99", "count"->"10")).get()
    actionMetrics.getStatus shouldBe 200
    
    actionMetrics = actionMetric.path("/actionMetrics/topActiveProjects", Map("count"->"0")).get()
    actionMetrics.getStatus shouldBe 500
    
  }
}
