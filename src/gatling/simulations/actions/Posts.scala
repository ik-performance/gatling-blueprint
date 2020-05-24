package actions

import io.gatling.http.Predef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

object Posts {

  private val commentsFeeder = separatedValues("comments.csv", '#')
  private val posts = csv("posts.csv").eager.random

  val allPosts =
    exec(http("request all posts")
      .get("/posts"))

  val validatePosts =
    exec(
        http("request all posts")
          .get("/posts")
          .check(jsonPath("$[*]").ofType[Map[String, Any]].findAll.saveAs("posts"))
    )

  val updatePost =
    feed(posts)
      .exec(
        http("Update Post")
          .post("/posts/${id}")
          .headers(Map(HttpHeaderNames.ContentType -> "application/json; charset=UTF-8"))
          .body(StringBody(
            """{
              "id": "${id}",
              "userId": "${userId}",
              "title": "${title}",
              "body": "${body}
            }""".stripMargin))
          .check(bodyString.saveAs("new_post"))
      )

  val userJourney =
    exec(
      http("Get all posts")
        .get("/posts")
        .check(jsonPath("$[*]").ofType[Map[String, Any]].findAll.saveAs("posts"))
    )
    .exec((session: Session) => {
      // use session expression to debug response
      val postsMap = session("posts").as[Vector[Map[String, Any]]]
      println("============ Sample post from list ============")
      println(postsMap(0))
      session
    })
      .exec(repeat(3) {
        feed(commentsFeeder)
          .exec(
            http("Get one post")
              .get("/posts/${posts.random().id}")
              .check(jsonPath("$.id").saveAs("one_post_id"))
          )
          .pause(10 second, 17 seconds)
          .exec(
            http("Read comments of post [${one_post_id}]")
              .get("/posts/${one_post_id}/comments")
          )
          .pause(8 second, 12 seconds)
          .exec(
            http("Add comment")
              .post("/posts/${one_post_id}/comments")
              .headers(Map(HttpHeaderNames.ContentType -> "application/json; charset=UTF-8"))
              .body(StringBody(
                """{
              "name": "gatling",
              "email": "gatling@test.pl",
              "body": "${body}"
            }""".stripMargin))
              .check(jsonPath("$.body").is("${body}"))
              .check(bodyString.saveAs("new_comment"))
          )
          .pause(8 second, 13 seconds)
          .exec((session: Session) => {
            println("============ New comment ============")
            println(session("new_comment").as[String])
            session
          })
      })
}
