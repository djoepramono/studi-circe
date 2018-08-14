package com.studikode.studicirce

import io.circe.parser
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

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

    implicit val studentDecoder: Decoder[Student] = deriveDecoder[Student]
    val decodingResult = parser.decode[List[Student]](inputString)

    decodingResult match {
      case Right(students) => students.map(println)
      case Left(error) => println(error.getMessage())
    }
  }
}
