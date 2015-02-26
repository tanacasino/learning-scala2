package com.github.tanacasino.study.tamaki

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Try, Failure, Success}

/**
 * Created by shintaro.tamaki on 2015/01/27.
 */
object Function {

  def main(args: Array[String]) {
    test00
    test01
    test02
    test03_0
  }

  def areaV3(pi: Double, radius: Double): Double = {
    pi * radius * radius
  }
  def areaV3_314 = areaV3(3.14, _: Double)
  def test00(): Unit = {
    println(areaV3_314(10))
  }

  val f = (x: Int) => x + 1
  val g = (x: Int) => x * 3
  val h1 = f compose g
  val h2 = g andThen f
  def test01(): Unit = {
    println(h1(10))
    println(h2(10))
  }

  def test02(): Unit = {
    val f1 = (x: Int) => { x + 1 }
    val f2 = (x: Int) => { x * 2 }
    val f3 = (x: Int) => { x - 1 }

    def chainFunction[T](functions: Function1[T, T]*) = {
      functions.reduceLeft(_ andThen _)
    }
    val f4 = chainFunction(f1, f2, f3)
    println(f4(10)) // => 21
  }

  def test03_0(): Unit = {
    def replace(langs:Seq[String], pf1: PartialFunction[String, String]) = {
      langs.map(p => if (pf1.isDefinedAt(p)) pf1(p) else p)
    }
    val langs = Seq("Java", "JavaScript", "Scala")
    val result = replace(langs, { case "Scala" => "Cool" })
    println(result) // => Seq(Java, JavaScript, Cool)
  }

  // もう1パターン
  def test03_1(): Unit = {
    def replace(langs:Seq[String], pf1: PartialFunction[String, String]) = {
      ???
    }
    val langs = Seq("Java", "JavaScript", "Scala")
    val result = replace(langs, { case "Scala" => "Cool" })
    println(result) // => Seq(Java, JavaScript, Cool)
  }

}