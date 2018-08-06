package com.studikode.studicirce

import io.circe.parser.decode
import io.circe.Decoder
import io.circe.HCursor

case class Team(name: String, members: List[Contestant])
case class Contestant(first_name: String, last_name: String)

object NestedDecoder {
  def main(args: Array[String]): Unit = {
    val input =
      """
      {
        "name": "A Team",
        "members": [
          {
            "first_name": "John",
            "last_name": "Doe"
          },
          {
            "first_name": "Mary",
            "last_name": "Jane"
          }
        ]
      }
      """.stripMargin

    // won't work
    //implicit val argh: Decoder[List[Contestant]] = decode[List[Contestant]]

    // works fine with lazy
    // otherwise it's forward reference extends over definition of value teamDecoder
    // basically it's not lazy enough for scala
    // https://stackoverflow.com/questions/35111887/scala-error-forward-reference-extends-over-definition-of-value-when-code-appe
    implicit lazy val teamDecoder: Decoder[Team] = (c: HCursor) => for {
      name <- c.get[String]("name")
      contestants <- c.get[List[Contestant]]("members")
    } yield Team(name, contestants)

    // this one also needs lazy
    implicit lazy val contestantDecoder: Decoder[Contestant] = (c: HCursor) =>
    for {
      firstName <- c.get[String]("first_name")
      lastName <- c.get[String]("last_name")
    } yield Contestant(firstName, lastName)

    val decodeResult = decode[Team](input)

    decodeResult match {
      case Right(book) => println(book)
      case Left(error) => println(error.getMessage())
    }
  }
}
