package actions

import io.gatling.http.Predef._
import io.gatling.core.Predef._

object Version {


  val validate =
    exec(http("Version Check")
      .get("/__version__"))
}

