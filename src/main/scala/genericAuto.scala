package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder
import io.circe.generic.auto._

case class Applicant(firstName: String, lastName: Option[String])

object GenericAutoDecoder {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      [
        {
          "firstName": "Marrie",
          "lastName": "Jane"
        },
        {
          "firstName": "Pete"
        },
        {
          "firstName": "Gwent",
          "lastName": null
        }
      ]
      """.stripMargin

    val decodingResult = parser.decode[List[Applicant]](inputString)

    decodingResult match {
      case Right(applicants) => applicants.map(println)
      case Left(error) => println(error.getMessage())
    }
  }
}
