package com.studikode.studicirce

import io.circe.parser.decode
import io.circe.generic.semiauto._


object SimpleDecoder {
  def main(args: Array[String]): Unit = {
    val input =
      """
        {
          "name": "John Doe"
        }
      """

    implicit val librarianDecoder = deriveDecoder[Librarian]
    val librarian = decode[Librarian](input)

    println(librarian)
  }
}
