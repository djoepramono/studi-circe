package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder

case class Student(name: String)

object ArrayDecoder {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      [
        {
          "name": "Bob"
        },
        {
          "name": "Barbara"
        }
      ]
      """.stripMargin

    // you can use deriveDecoder like below
    // implicit val studentDecoder = deriveDecoder[List[Student]]

    // or you can write your own decoder
    implicit val studentDecoder: Decoder[Student] = (hCursor: HCursor) => {
      for {
        name <- hCursor.get[String]("name")
      } yield Student(name)
    }

    val decodingResult = parser.decode[List[Student]](inputString)

    decodingResult match {
      case Right(students) => students.map(println)
      case Left(error) => println(error.getMessage())
    }
  }
}
