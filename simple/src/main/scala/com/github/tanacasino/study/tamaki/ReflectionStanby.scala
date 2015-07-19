package com.github.tanacasino.study.tamaki

/**
 * Created by tamaki on 2015/02/23.
 */
object ReflectionStanby {

  def main(args: Array[String]): Unit = {
    println(Q5())
    println(ReflectionQ5())
  }

  def Q5(): scala.collection.mutable.HashMap[String, String] = {
    val x1: String = "abc"
    val x2: String = ""
    val x3: Option[String] = Some("abc")
    val x4: Option[String] = Some("")
    val x5: Option[String] = Some("12345")
    val x6: Option[String] = Some("not a number")
    val errors = new scala.collection.mutable.HashMap[String, String]()
    if(x1.isEmpty) {
      errors.put("x1", "error.required")
    }
    if(x2.isEmpty) {
      errors.put("x2", "error.required")
    }
    if(x3.isDefined && !x3.get.isEmpty) {
      errors.put("x3", "error.required")
    }
    if(x4.isDefined && !x4.get.isEmpty) {
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

  def ReflectionQ5(): Map[String, String] = {
    val x1: String = "abc"
    val x2: String = ""
    val x3: Option[String] = Some("abc")
    val x4: Option[String] = Some("")
    val x5: Option[String] = Some("12345")
    val x6: Option[String] = Some("not a number")
    Seq(
      requiredCheck(Option(x1), "x1"),
      requiredCheck(Option(x2), "x2"),
      requiredCheck(x3, "x3"),
      requiredCheck(x4, "x4"),
      numberCheck(x5, "x5"),
      numberCheck(x6, "x6")
    )
    .flatten
    .toMap
  }

  def requiredCheck(value:Option[String], key:String): Option[(String, String)] = {
    value.map {
      _.isEmpty match {
        case true => Some(key, "error.required")
        case false => None
      }
    }
    .flatten
  }
  def numberCheck(value:Option[String], key:String): Option[(String, String)] = {
    value.map {
      _.matches("""\d+""") match {
        case true => None
        case false => Some(key, "error.number")
      }
    }
    .flatten
  }

}
