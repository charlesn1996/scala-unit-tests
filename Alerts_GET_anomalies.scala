package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Alerts_GET_anomalies extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/alerts/anomalies" should "have the anomalies alerts" in {

    val alert = Interset.login("j04", "j04")
    var alerts: Response = alert
      .path("/alerts/anomalies", Map("count"->"100", "orderBy"->"risk", "direction"->"desc"))
      .get()
    alerts.getStatus shouldBe 200
    
    
    var json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-12-12T20:00:00Z"
    var risk = json.findPath("risk").asDouble() *100
    risk.intValue() shouldBe 100
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 10
    json.findValue("userId").asInt() shouldBe 2
    json.findValue("userName").asText() shouldBe "archie"
    json.findValue("projectId").asText() shouldBe "1"
    json.findValue("projectName").asText() shouldBe "project-2"
    json.findValue("text").asText() shouldBe "user mooched a project"
    json.findValue("category").asText() shouldBe "mooching"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 0
    json.findValue("moochingScore").asInt() shouldBe 0
    json.findValue("hoardingScore").asInt() shouldBe 0
    
    alerts = alert.path("/alerts/anomalies", Map("userId"->"2", "count"->"100", "orderBy"->"risk", "direction"->"desc")).get()
    
    json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-12-12T20:00:00Z"
    risk = json.findPath("risk").asDouble() *100
    risk.intValue() shouldBe 100
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 10
    json.findValue("userId").asInt() shouldBe 2
    json.findValue("userName").asText() shouldBe "archie"
    json.findValue("projectId").asText() shouldBe "1"
    json.findValue("projectName").asText() shouldBe "project-2"
    json.findValue("text").asText() shouldBe "user mooched a project"
    json.findValue("category").asText() shouldBe "mooching"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 0
    json.findValue("moochingScore").asInt() shouldBe 0
    json.findValue("hoardingScore").asInt() shouldBe 0
    
    alerts = alert.path("/alerts/anomalies", Map("projectId"->"2","userId"->"2", "count"->"100", "orderBy"->"risk", "direction"->"desc")).get()
    
    json = alerts.readEntity(classOf[JsonNode])
    json.findValue("time").asText() shouldBe "2014-11-25T16:00:00Z"
    risk = json.findPath("risk").asDouble() *100
    risk.intValue() shouldBe 100
    json.findValue("alertType").asText() shouldBe "anomaly"
    json.findValue("subtype").asInt() shouldBe 10
    json.findValue("userId").asInt() shouldBe 3
    json.findValue("userName").asText() shouldBe "camilla"
    json.findValue("projectId").asText() shouldBe "2"
    json.findValue("projectName").asText() shouldBe "project-3"
    json.findValue("text").asText() shouldBe "user mooched a project"
    json.findValue("category").asText() shouldBe "mooching"
    json.findValue("sneakingScore").asInt() shouldBe 0
    json.findValue("wanderingScore").asInt() shouldBe 0
    json.findValue("moochingScore").asInt() shouldBe 0
    json.findValue("hoardingScore").asInt() shouldBe 0
    
  }
}