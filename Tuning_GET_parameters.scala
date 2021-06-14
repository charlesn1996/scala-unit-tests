package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}


class Tuning_GET_parameters extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Interset.assertTenant("J04")
  }
  

  "/tuning/parameters" should "have the default parameters" in {

    val tuning = Interset.login("j04", "j04")
    var tunings: Response = tuning
      .path("/tuning/parameters")
      .get()
    tunings.getStatus shouldBe 200
    
   
    var json = tunings.readEntity(classOf[JsonNode])
    // System.out.println(json.toString())
    json.findValuesAsText("name").toString() shouldBe "[AGG MAC B0, AGG MAC B1, AGG PRO B0, AGG PRO B1, AGG USR B0, AGG USR B1, BASE VULN, DECAY RATE, HOURLY WEIGHT, N, PERSIS MAC B0, PERSIS MAC B1, PERSIS PRO B0, PERSIS PRO B1, PERSIS USR B0, PERSIS USR B1, PRIOR BAD, SCALING FACTOR, WORK DAY SCALE, WORK HR SCALE]"
    json.findValuesAsText("value").toString() shouldBe "[-6.4, 1.45, -6.4, 1.45, -6.4, 1.45, 0.0, 0.99, 0.995, 17.0, -3.0, 3.0, -3.0, 3.0, -3.0, 3.0, 0.01, 50.0, 1.0, 1.2]"
    
    json.findValuesAsText("name").size() shouldBe 20
    json.findValuesAsText("value").size() shouldBe 20
    
  }
}