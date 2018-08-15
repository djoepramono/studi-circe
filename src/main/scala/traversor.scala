package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder
import io.circe.Json

case class Member(name: String)

object JsonTraversor {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      {
        "name": "Group A",
        "members": [
          {
            "name": "Angelo",
            "age": 24
          },
          {
            "name": "Angela",
            "age": 20
          }
        ]
      }
      """.stripMargin

    implicit val memberDecoder: Decoder[Member] = (hCursor: HCursor) => {
      for {
        name <- hCursor.get[String]("name")
      } yield Member(name)
    }

    val json: Json = parser.parse(inputString).getOrElse(Json.Null)
    val members: Option[Json] = json.hcursor.downField("members").focus

    members match {
      case None => println("cannot find members in the json");
      case Some(members) => {
        val maybeMemberList = members.hcursor.as[List[Member]]
        maybeMemberList match {
          case Right(members) => println(members)
          case Left(error) => println(error.getMessage)
        }
      }
    }
  }
}
