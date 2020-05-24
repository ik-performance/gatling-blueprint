
package config

import scala.util.Properties

trait Environment {

  val baseUrl: String = Properties.envOrElse("HOST_NAME", "https://www.xebia.com")
  val loadFactor: Double = Properties.envOrElse("LOAD_FACTOR", "1").toDouble
  val durationInMinutes: Int = Properties.envOrElse("DURATION_MINUTES", "1").toInt

}
