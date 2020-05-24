import actions.Address
import config.Config
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._
import actions.Version

class VersionWarmupSimulation extends Simulation {

  val duration = java.lang.Long.getLong("duration", 10L)
  val users = Integer.getInteger("users", 5)
  val rampup = java.lang.Long.getLong("rampup", 10L)

  val headers = Map(
    "Content-Type" -> "application/json;charset=UTF-8",
    "PreferredLanguage" -> "en-us")

  private val httpConf = http.baseUrl(Config.stageUrl)
    .acceptHeader("application/json")
    .userAgentHeader("Chrome/68.0.3440.106 Safari/537.36")

  private val scn: ScenarioBuilder = scenario("version check")
    .exec(Version.validate)
    .pause(1000.milliseconds, 7000.milliseconds)

  setUp(
    scn.inject(
//      atOnceUsers(10),
//      constantUsersPerSec(10) during (30 minutes),
      constantUsersPerSec(12) during (10 minutes),
    )
  ).protocols(httpConf)
}
