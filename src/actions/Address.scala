package actions

import io.gatling.core.body.StringBody
import io.gatling.http.Predef._
import io.gatling.core.Predef._

object Address {

  val address = csv("address.csv").eager.random

  val validate =
    feed(address)
      .exec(http("Validate Address")
        .post("/api/addressvalidate")
        .headers(Map(HttpHeaderNames.ContentType -> "application/json"))
        .body(StringBody(
          """{
            "address1": "${address1}",
            "address2": "${address2}",
            "city": "${city}",
            "province_code": "${state}",
            "zip": "${zip}"
          }""".stripMargin
        )))
}

