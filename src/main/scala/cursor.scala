package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder
import io.circe.Json

case class Member(name: String)
case class Group(name: String, members: List[Member])

object MemberDecoder {
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

    // or you can write your own decoder

    implicit lazy val memberDecoder: Decoder[Member] = (hCursor: HCursor) => {
      for {
        name <- hCursor.get[String]("name")
      } yield Member(name)
    }

    //get[A](key) as shorthand for downField(key).as[A].

    // val decodingResult = parser.decode[List[Member]](inputString)(membersDecoder)

    val json: Json = parser.parse(inputString).getOrElse(Json.Null)
    val members: Option[Json] = json.hcursor.downField("members").focus

    // focus => cursor to Json
    // hcursor => json to cursor
    members match {
      case None => println("cannot find members in the json");
      case Some(members) => {
        // println(members.hcursor.downArray.right.as[Member])
        val maybeMemberList = members.hcursor.as[List[Member]]
        maybeMemberList match {
          case Right(members) => println(members)
          case Left(error) => println(error.getMessage)
        }

      }
    }

    // could not find implicit value for parameter d:
    // means there's no implicit


    // println(inputString)

    // println(decodingResult)
    //
    // decodingResult match {
    //   case Right(students) => students.map(println)
    //   case Left(error) => println(error.getMessage())
    // }
  }
}
