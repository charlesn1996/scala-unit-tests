package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.TuningWeight

class Tuning_PUT_weights_anomalyType extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/weights/<anomalyType>" should "have the ability to set a weight for an anomaly" in {

    val tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/weights/11")
      .get()
    tunings.getStatus shouldBe 200
    
   
    var json = tunings.readEntity(classOf[JsonNode])
    json.path("anomalyType").asInt() shouldBe 11
    json.path("weight").asInt() shouldBe 1
    
    val t = new TuningWeight()
    t.anomalyType = 11
    t.weight = 2
    
    tunings = tuning.path("/tuning/weights/11").put(Entity.json(t))
    tunings.getStatus shouldBe 200
    
    json = tunings.readEntity(classOf[JsonNode])
    json.path("anomalyType").asInt() shouldBe 11
    json.path("weight").asInt() shouldBe 2
    
    val s = new TuningWeight()
    s.anomalyType = 11
    s.weight = 1
    
    tunings = tuning.path("/tuning/weights/11").put(Entity.json(s))
    tunings.getStatus shouldBe 200
    
    json = tunings.readEntity(classOf[JsonNode])
    json.path("anomalyType").asInt() shouldBe 11
    json.path("weight").asInt() shouldBe 1
    
  }
}