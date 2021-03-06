package com.studikode.studicirce

import io.circe.parser
import io.circe.generic.semiauto.deriveDecoder
// alternatively, you can use the following instead of deriveDecoder
// import io.circe.generic.auto._

case class Staff(name: String)

object SimpleDecoder {
  def main(args: Array[String]): Unit = {

    val input =
      """
        {
          "name": "John Doe"
        }
      """.stripMargin

    implicit val staffDecoder = deriveDecoder[Staff]
    val decodingResult = parser.decode[Staff](input)

    decodingResult match {
      case Right(staff) => println(staff.name)
      case Left(error) => println(error.getMessage())
    }
  }
}
