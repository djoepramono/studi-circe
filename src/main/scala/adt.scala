package com.studikode.studicirce

import io.circe.parser
import io.circe.HCursor
import io.circe.Decoder

sealed trait Level
case object Beginner extends Level
case object Intermediate extends Level
case object Advanced extends Level

case class Error(value: String)

object Level {
  def fromString(value: String): Either[Error, Level] =
    value.toLowerCase match {
      case "beginner" => Right(Beginner)
      case "intermediate" => Right(Intermediate)
      case "advanced" => Right(Advanced)
      case _ => Left(Error("Unknown String"))
    }
}

object ADTDecoder {
  def main(args: Array[String]): Unit = {
    val inputString =
      """
      [
        "intermediate", "beginner","master"
      ]
      """.stripMargin

    implicit val ratingDecoder: Decoder[Either[Error,Level]] =
      (hCursor: HCursor) => hCursor.as[String].map(Level.fromString)

      // or if you prefer the long winded way
      // (hCursor: HCursor) => {
      //   for {
      //     possibleString <- hCursor.as[String]
      //     x = Level.fromString(possibleString)
      //   } yield x
      // }

    val decodingResult = parser.decode[List[Either[Error,Level]]](inputString)

    decodingResult match {
      case Right(maybeLevelList) =>
        maybeLevelList.map(maybeLevel => maybeLevel match {
          case Left(error) => println(error.value)
          case Right(level) => println(level)
        })
      case Left(error) => println(error.getMessage())
    }
  }
}
