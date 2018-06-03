package com.studikode.studicirce

case class Library (librarian: Librarian, books: Book)
case class Librarian (name: String)
case class Simple(name: String)
case class Book (title: String, tags: List[Tag])

sealed case class Tag()
object Scifi extends Tag
object Education extends Tag

object Tag {
  def fromString(s: String): Option[Tag] = {
    s match {
      case "scifi" => Some(Scifi)
      case "education" => Some(Education)
      case _ => None
    }
  }
}
