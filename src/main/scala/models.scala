package com.studikode.studicirce

case class Librarian (name: String)
case class TodayRoster ( onDuty: List[Librarian], backup: List[Librarian])

case class Library (librarian: Librarian, books: Book)

case class Book (title: String, tags: List[Tag])

object Tag {
  def fromString(s: String): Option[Tag] = {
    s match {
      case "scifi" => Some(Scifi)
      case "education" => Some(Education)
      case _ => None
    }
  }
}

sealed case class Tag()
object Scifi extends Tag
object Education extends Tag
