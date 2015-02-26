package com.github.tanacasino.study.hajimeni.function

/**
 * Created by nishiyama on 2015/02/26.
 */
object PartialFunction extends App {

  case class User(id: Int, name: String, isAdmin: Boolean)
  val users = List {
    User(1, "taro", true)
    User(2, "jiro", false)
    User(3, "saburo", true)
  }

  val names :List[String] = users.collect {
    case user if user.isAdmin => user.name
  }
  val names2 = users.filter(_.isAdmin).map(_.name)

  val pf1: PartialFunction[String, String] = { case "foo" => "bar" }
  val pf2: PartialFunction[String, String] = { case "hoge" => "fuga" }

  val pf3 = pf1 orElse pf2

  val langs = Seq("Java", "JavaScript", "Scala")
  val result = replace(langs, {case "Scala" => "Cool"})

  def replace(langs: Seq[String], pf: PartialFunction[String, String]) = {
    langs.map{ l => if (pf.isDefinedAt(l)) pf(l) else l }
  }

  println(result) // Seq(Java, JavaScript, Cool)
}
