package com.studikode.studicirce

import io.circe.parser
import io.circe.parser.decode
import io.circe.DecodingFailure
import io.circe.Decoder

object DecodeWithDecoder {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      {
        "books": [
          {
            "title": "Journey to the East"
            "tags": [
              { "tag": "scifi" },
            ]
          },
          {
            "title": "Pippa the Mathematician"
            "tags": [
              { "tag": "education" },
              { "tag": "fiction" }
            ]
          }
        ]
      }
      """.stripMargin

    // val inputString =
    //   """
    //   {
    //     "tag": "important"
    //   }
    //   """

    // val result = decode[Library](inputString)

    // println(result)

    // val tasks = parser.decode[Option[Tag]](inputString)(TagDecoder)
    //
    // tasks match {
    //   case Left(e) => println(s"rawr ${e.getMessage()}")
    //   case Right(a) => println(a)
    // }
  }
}
