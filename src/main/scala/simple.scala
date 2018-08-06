package com.studikode.studicirce

import io.circe.parser.decode
import io.circe.generic.semiauto.deriveDecoder

case class Staff(name: String)

object SimpleDecoder {
  def main(args: Array[String]): Unit = {

    val input =
      """
        {
          "name": "John Doe"
        }
      """.stripMargin

    // Using the derived encoder from Circe,
    // we can just pass the case class as type parameter
    // Yes, you need the implicit function
    implicit val staffDecoder = deriveDecoder[Staff]
    val decodeResult = decode[Staff](input)

    decodeResult match {
      case Right(staff) => println(staff.name)
      case Left(error) => println(error.getMessage())
    }
  }
}
