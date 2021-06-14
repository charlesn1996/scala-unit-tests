package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Tuning_GET_weights extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/weights" should "have the default tuning weights" in {

    val tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/weights")
      .get()
    tunings.getStatus shouldBe 200
    
   
    var json = tunings.readEntity(classOf[JsonNode])
    // System.out.println(json.toString())
    json.findValuesAsText("anomalyType").toString() shouldBe "[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]"
    json.findValuesAsText("weight").toString() shouldBe "[1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0]"
    
    json.findValuesAsText("anomalyType").size() shouldBe 17
    json.findValuesAsText("weight").size() shouldBe 17
    
  }
}