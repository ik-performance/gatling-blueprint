package requests

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import scenarios.RequestBase

trait StaticRequests extends RequestBase {

  val openHomePage: ChainBuilder =
    exec(
      http("Open homepage").
        get("/")
        .check(status.is(200))
    ).exitHereIfFailed

}
