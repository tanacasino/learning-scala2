package com.github.tanacasino.study.tanacasino.function

import scala.concurrent.Future


object Func {

  def main (args: Array[String]) {
    // 引数 0
    val f0: Function0[String] = () => "関数"
    val f0_2: () => String = () => "関数"
    val f0_3: Function0[String] = new Function0[String] {
      def apply() = {
        "関数"
      }
    }

    val xx = f0_3.apply()
    val yy = f0_3()

    // 引数 1
    val f1: Function1[Int, String] = (n: Int) => n.toString
    val f1_2: Function1[Int, String] = n => n.toString
    val f1_3: Function1[Int, String] = new Function1[Int, String] {
      def apply(n: Int): String = {
        n.toString
      }
    }


    val f = Future.successful("BadRequest")
    // 引数 2
    val f2: Function2[Int, Int, Int] = (x: Int, y: Int) => x + y
    val f2_3: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
      def apply(x: Int, y: Int): Int = {
        x + y
      }
    }


    timeit {
      Thread.sleep(100)
      println("Hello, World!")
    }

    def timeit(f: => Unit): Unit = {
      val start = System.currentTimeMillis()
      println(s"start: $start")
      f
      val end = System.currentTimeMillis()
      println(s"end: $end")
      println(s"time: ${end - start}")
    }



    val area314 = areaV2(3.14) _
    println(area314(10))
    val area3 = areaV2(3.0) _
    area3(10)

    println("V3")
    val areaV3_314 = areaV3(3.14, _: Double)
    println(areaV3_314(10))



    println("--- compose ---")
    composeTest
  }


  def composeTest = {
    val f = (x: Int) => x + 2
    val g = (x: Int) => x * 2
    val h = f compose g
    println(f(g(10)))
    println(h(10))
  }





  def areaV2(pi: Double)(radius: Double): Double = {
    pi * radius * radius
  }

  def areaV3(pi: Double, radius: Double): Double = {
    pi * radius * radius
  }


}
