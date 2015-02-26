package com.github.tanacasino.study.koga.function

import scala.util.{Failure, Success, Try}

/**
 * Created by tomoki.koga on 15/02/26.
 */
object Partial {

  def main(args: Array[String]) {

    val langs = Seq("Java", "JavaScript", "Scala")
    val result = replace(langs, { case "Scala" => "Cool" })
    println(result)

    assert(Seq("Java", "JavaScript", "Cool") == result)
  }

  def replace(langs: Seq[String], pf: PartialFunction[String, String]): Seq[String] = {

    def tryReplaceLang(lang: String): Option[String] =
      Try(pf(lang)).orElse(Try(lang)).toOption

    langs.flatMap(tryReplaceLang)
  }
}
