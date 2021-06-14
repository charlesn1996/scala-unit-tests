package com.interset.test.J04

import javax.ws.rs.core.Response
import com.fasterxml.jackson.databind.JsonNode
import com.interset.test.Interset
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import javax.ws.rs.client.Entity
import scala.util.parsing.json.JSONObject
import play.api.libs.json.{JsObject, Json}
import com.interset.test.People

class Events_GET_csv extends People {


  "/events" should "have the events CSV" in {

    val event = Interset.login("j04", "j04")
    var events: Response = event
      .path("/events", Map("userId"->personList(1), "count"->"10000"))
      .get()
    events.getStatus shouldBe 200
    
    

    events = event.path("/events", Map("userId"->personList(1), "count"->"10000")).header("Accept", "text/csv").get()
   
    var json = events.readEntity(classOf[String])
    
    var lines = json.split("\n");
    var matchLine = "";
    
    def fineLine {
    lines.foreach{line => if(line.contains("2014-11-24T15:00:00.000Z")) {
      //System.out.println("Matched : " + line)
      matchLine = line; 
      return}}
    }
    fineLine
    
    matchLine.contains("archie") shouldBe true
    matchLine.contains("project-1") shouldBe true
    matchLine.contains("add") shouldBe true
    matchLine.contains("0.0.0.0") shouldBe true
    matchLine.contains("(none)") shouldBe true
    matchLine.contains("1") shouldBe true
    matchLine.contains("0") shouldBe true
    
   
  }
}