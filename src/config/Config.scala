package config

import com.typesafe.config.ConfigFactory

object Config {
  val config = ConfigFactory.load("static-config").getConfig("local")
  val baseUrl = config.getString("baseUrl")
}
