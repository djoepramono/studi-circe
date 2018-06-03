package com.studikode.studicirce

import io.circe.HCursor
import io.circe.Decoder
import io.circe.DecodingFailure

object TagDecoder extends Decoder[Option[Tag]] {
  def apply(c: HCursor): Decoder.Result[Option[Tag]] =
    c.as[String].map(Tag.fromString)
}
