package actions

import io.gatling.http.Predef._
import io.gatling.core.Predef._

object HeartBeat {
  val beat =
    exec(http("HeartBeat Check")
      .get("/__heartbeat__"))
    exec(http("Version Check")
      .get("/__version__"))
}
