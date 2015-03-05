package com.github.tanacasino.study.kitaly.random

import scala.util.Try

/**
 * Created by kitagawa on 15/02/17.
 */
object Option17thFeb {

  def main(args: Array[String]) {

    // Input Data
    val x3: Option[String] = Some("")
    val x5: Option[String] = Some("abc")
    
    otehon
    
    def otehon = {
      val res3 = x3.filter(_.isEmpty).map(_ => ("x3", "error.required"))
      val res5 = x5.flatMap(s => Try(s.toInt).failed.toOption).map(_ => ("x5", "error.number"))
      
      println(res3, res5)
    }
    
    def kitalyTry() = {
      // Validation
      val either3 = x3.filter(_.isEmpty).toRight(("x3", "error.required"))

      val either5 = Try(
        x5.withFilter(_.nonEmpty).map(_.toInt)
      ).toOption.flatten.toRight(("x5", "error.number"))

      // Merge validation errors into a map
      val errMap = List(either3, either5)
        .withFilter(_.isLeft) map {
        case Left((key, value)) => (key, value)
      } toMap

      // Print out results
      println(either3)
      println(either5)
      println(errMap)
    }

  }

}
