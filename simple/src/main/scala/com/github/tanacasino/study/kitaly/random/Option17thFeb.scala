package com.github.tanacasino.study.kitaly.random

import scala.util.Try

/**
 * Created by kitagawa on 15/02/17.
 */
object Option17thFeb {

  def main(args: Array[String]) {

    val x3: Option[String] = Some("")
    val x5: Option[String] = Some("123")
    
    val either3 = x3.filter(_.nonEmpty).toRight(("x3", "error.required"))
    val either5 = Try( 
      x5.filter(_.nonEmpty).map(_.toInt)
    ).toOption.flatten.toRight(("x5", "error.number"))

    
    println(either3.toString)
    println(either5.toString)
    
    val errMap = List(either3, either5).filter(_.isLeft) map {
      case Left((key, value)) => (key, value)
    } toMap
    
    println(errMap)
  }

}
