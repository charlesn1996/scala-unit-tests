package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.TuningParameter

class Tuning_PUT_parameter_name extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/parameters/<name>" should "have the ability to set a specific parameter" in {

    var tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/parameters/N")
      .get()
    tunings.getStatus shouldBe 200
    
    var json = tunings.readEntity(classOf[JsonNode])
    json.path("name").asText() shouldBe "N"
    json.path("value").asText() shouldBe "17.0"
    
    val t = new TuningParameter()
    t.name = "N"
    t.value = 7.77
    
    tunings = tuning.path("/tuning/parameters/N").put(Entity.json(t))
    tunings.getStatus shouldBe 200
    
    json = tunings.readEntity(classOf[JsonNode])
    json.path("name").asText() shouldBe "N"
    json.path("value").asText() shouldBe "7.77"
    
    val s = new TuningParameter()
    s.name = "N"
    s.value = 17
    
    tunings = tuning.path("/tuning/parameters/N").put(Entity.json(s))
    tunings.getStatus shouldBe 200
    
    json = tunings.readEntity(classOf[JsonNode])
    json.path("name").asText() shouldBe "N"
    json.path("value").asText() shouldBe "17.0"
    
  }
}