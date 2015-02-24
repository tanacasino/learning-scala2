package com.github.tanacasino.study.tanacasino.quiz

import scala.util.Try


object Quiz {

  def main(args: Array[String]) {
    q5()
  }

  def q5() = {
    val x1: String = "abc"
    val x2: String = ""
    val x3: Option[String] = Some("abc")
    val x4: Option[String] = Some("")
    val x5: Option[String] = Some("12345")
    val x6: Option[String] = Some("not a number")

    def before() = {
      val errors = new scala.collection.mutable.HashMap[String, String]()
      if(x1.isEmpty) {
        errors.put("x1", "error.required")
      }
      if(x2.isEmpty) {
        errors.put("x2", "error.required")
      }
      if(x3.isDefined && x3.get.isEmpty) {
        errors.put("x3", "error.required")
      }
      if(x4.isDefined && x4.get.isEmpty) {
        errors.put("x4", "error.required")
      }
      if(x5.isDefined && !x5.get.isEmpty) {
        try {
          x5.get.toInt
        } catch {
          case e: Exception =>
            errors.put("x5", "error.number")
        }
      }
      if(x6.isDefined && !x6.get.isEmpty) {
        try {
          x6.get.toInt
        } catch {
          case e: Exception =>
            errors.put("x6", "error.number")
        }
      }
      errors
    }

    def after() = {
      // 別名をつける. ちゃんとやるなら Error用のtrait/classを作った方がよい
      type ValidationError = (String, String)

      // これはライブラリやFW部分などに局所的に使うやつなので、あんまり使うと嫌な顔されるので注意
      implicit def string2Option(s: String): Option[String] = Option(s)

      def nonEmptyRequired(key: String, value: Option[String]): Option[ValidationError] = {
        value.filter(_.isEmpty).map(_=> (key, "error.required"))
      }

      def numberRequired(key: String, value: Option[String]): Option[ValidationError] = {
        value.flatMap(s => Try(s.toInt).failed.toOption.map(_ => (key, "error.number")))
      }

      Seq(
        nonEmptyRequired("x1", x1),
        nonEmptyRequired("x2", x2),
        nonEmptyRequired("x3", x3),
        nonEmptyRequired("x4", x4),
        numberRequired("x5", x5),
        numberRequired("x6", x6)
      )
      .flatten
      .toMap
    }


    val expected = before()
    println(expected)
    val actual = after()
    println(actual)
    assert(expected == actual)
  }

}
