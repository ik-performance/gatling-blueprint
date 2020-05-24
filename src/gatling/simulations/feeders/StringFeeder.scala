package feeders

trait StringFeeder {

  def randomString(len: Int): String = scala.util.Random.alphanumeric.take(len).mkString

  val stringFeeder: Iterator[Map[String, String]] = Iterator.continually(Map("randomString" -> randomString(10)))

}
