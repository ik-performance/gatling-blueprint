import actions.Posts
import config.Config
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

class userJourney extends Simulation {

  private val httpConf = http
    .baseUrl(Config.baseUrl)
    .acceptHeader("application/json")
    .doNotTrackHeader("1")
    .userAgentHeader("Chrome/68.0.3440.106 Safari/537.36")

  private val scn: ScenarioBuilder =
    scenario("user journey")
      .exec(Posts.userJourney)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
