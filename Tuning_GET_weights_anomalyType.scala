package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Tuning_GET_weights_anomalyType extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/weights/<anomalyType>" should "have the default tuning weight for a specific anomaly found" in {

    val tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/weights/11")
      .get()
    tunings.getStatus shouldBe 200
   
    var json = tunings.readEntity(classOf[JsonNode])
    // System.out.println(json.toString())
    json.findValuesAsText("anomalyType").toString() shouldBe "[11]"
    json.findValuesAsText("weight").toString() shouldBe "[1.0]"
    
  }
}