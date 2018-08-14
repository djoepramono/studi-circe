package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder

case class Citizen(name: String, age: Int)

object ManualDecoder {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      [
        {
          "name": "Maggie",
          "age": 33,
          "country": "Australia"
        },
        {
          "name": "Moggie",
          "age": 17,
          "country": "New Zealand"
        }
      ]
      """.stripMargin

    implicit val citizenDecoder: Decoder[Citizen] = (hCursor: HCursor) => {
      for {
        name <- hCursor.get[String]("name")
        age <- hCursor.downField("age").as[Int]
      } yield Citizen(name, age)
    }

    val decodingResult = parser.decode[List[Citizen]](inputString)

    decodingResult match {
      case Right(citizens) => citizens.map(println)
      case Left(error) => println(error.getMessage())
    }
  }
}
