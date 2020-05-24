import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import config.Config
import actions.Posts

class validate extends Simulation {

  private val httpConf = http
    .baseUrl(Config.baseUrl)
    .acceptHeader("application/json")
    .doNotTrackHeader("1")
    .userAgentHeader("Chrome/68.0.3440.106 Safari/537.36")

  private val scn: ScenarioBuilder =
    scenario("validate all posts")
      .exec(Posts.validatePosts)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
