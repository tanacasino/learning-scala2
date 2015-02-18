package com.github.tanacasino.study.kitaly.random

/**
 * Created by kitagawa on 15/02/18.
 */
object TimeIt {

  def main(args: Array[String]) {
    timeit {
      Thread.sleep(1000)
      println("Hellow, World")
    }
  }
  
  def timeit[A](f: => A):A = {
    val start: Long = System.currentTimeMillis()
    println(s"start: $start")
    
    val res = f
    
    val end = System.currentTimeMillis()
    println(s"end: $end")
    println(s"time spent: ${end - start}")
    
    res
  }
}
