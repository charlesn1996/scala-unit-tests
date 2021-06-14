package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Tuning_GET_parameter_name extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/parameters/<name>" should "have a specific parameter" in {

    val tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/parameters/AGG%20PRO%20B0")
      .get()
    tunings.getStatus shouldBe 200
    
   
    var json = tunings.readEntity(classOf[JsonNode])
    // System.out.println(json.toString())
    json.path("name").asText() shouldBe "AGG PRO B0"
    json.path("value").asText() shouldBe "-6.4"
    
  }
}