package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Alerts_GET_aggregates extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/alerts/aggregates" should "have the correct aggregates alerts" in {

    val alert = Interset.login("j04", "j04")
    var alerts: Response = alert
      .path("/alerts/aggregates", Map("count"->"100", "orderBy"->"risk", "direction"->"desc"))
      .get()
    alerts.getStatus shouldBe 200
    
   
    var json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-12-12T16:00:00Z"
    var risk = json.findPath("risk").asDouble() *100
    risk.intValue() shouldBe 21
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 0
    json.findValue("userId").asInt() shouldBe 2
    json.findValue("userName").asText() shouldBe "archie"
    json.findValue("projectId").asText() shouldBe "-1"
    json.findValue("projectName").asText() shouldBe "(none)"
    json.findValue("text").asText() shouldBe "aggregate anomaly"
    json.findValue("category").asText() shouldBe "Story"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 6
    json.findValue("moochingScore").asInt() shouldBe 47
    json.findValue("hoardingScore").asInt() shouldBe 47
    
    alerts = alert.path("/alerts/aggregates", Map("userId"->"2", "count"->"100", "orderBy"->"risk", "direction"->"desc")).get()
    
    json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-12-12T16:00:00Z"
    risk = json.findPath("risk").asDouble() *100
    risk.round shouldBe 21
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 0
    json.findValue("userId").asInt() shouldBe 2
    json.findValue("userName").asText() shouldBe "archie"
    json.findValue("projectId").asText() shouldBe "-1"
    json.findValue("projectName").asText() shouldBe "(none)"
    json.findValue("text").asText() shouldBe "aggregate anomaly"
    json.findValue("category").asText() shouldBe "Story"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 6
    json.findValue("moochingScore").asInt() shouldBe 47
    json.findValue("hoardingScore").asInt() shouldBe 47
    
    alerts = alert.path("/alerts/aggregates", Map("projectId"->"2", "count"->"100", "orderBy"->"risk", "direction"->"desc")).get()
    
    json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-11-26T15:00:00Z"
    risk = json.findPath("risk").asDouble() *100
    risk.round shouldBe 1
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 0
    json.findValue("userId").asInt() shouldBe -1
    json.findValue("userName").asText() shouldBe "(none)"
    json.findValue("projectId").asText() shouldBe "2"
    json.findValue("projectName").asText() shouldBe "project-3"
    json.findValue("text").asText() shouldBe "aggregate anomaly"
    json.findValue("category").asText() shouldBe "Story"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 0
    json.findValue("moochingScore").asInt() shouldBe 100
    json.findValue("hoardingScore").asInt() shouldBe 0
    
    alerts = alert.path("/alerts/aggregates", Map("projectId"->"2","userId"->"2", "count"->"100", "orderBy"->"risk", "direction"->"desc")).get()
    
    json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-11-26T15:00:00Z"
    risk = json.findPath("risk").asDouble() *100
    risk.round shouldBe 1
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 0
    json.findValue("userId").asInt() shouldBe -1
    json.findValue("userName").asText() shouldBe "(none)"
    json.findValue("projectId").asText() shouldBe "2"
    json.findValue("projectName").asText() shouldBe "project-3"
    json.findValue("text").asText() shouldBe "aggregate anomaly"
    json.findValue("category").asText() shouldBe "Story"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 0
    json.findValue("moochingScore").asInt() shouldBe 100
    json.findValue("hoardingScore").asInt() shouldBe 0
    
  }
}