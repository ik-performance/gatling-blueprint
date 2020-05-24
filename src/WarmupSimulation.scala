import actions.Address
import config.Config
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class WarmupSimulation extends Simulation {

  val duration = java.lang.Long.getLong("duration", 10L)
  val users = Integer.getInteger("users", 5)
  val rampup = java.lang.Long.getLong("rampup", 10L)

  val headers = Map(
    "Content-Type" -> "application/json;charset=UTF-8",
    "PreferredLanguage" -> "en-us")

  private val httpConf = http.baseUrl(Config.devUrl)
    .acceptHeader("application/json; charset=utf-8")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
    .doNotTrackHeader("1")
    .maxConnectionsPerHostLikeChrome
    .enableHttp2

  private val scn: ScenarioBuilder = scenario("warmup")
    .exec(Address.validate)
    .pause(1000.milliseconds, 50000.milliseconds)

  setUp(
    scn.inject(
      constantUsersPerSec(6) during (5 minutes),
      // constantConcurrentUsers(10) during (5 minutes),
    )
  ).protocols(httpConf)

}
