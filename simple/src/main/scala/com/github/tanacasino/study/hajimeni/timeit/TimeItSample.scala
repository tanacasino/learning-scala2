package com.github.tanacasino.study.hajimeni.timeit

/**
 * Created by nishiyama on 2015/02/18.
 */
object TimeItSample extends  App {

  def timeit[R](f: => R): R = {
    val start = System.currentTimeMillis()
    println(s"start: $start")
    val result = f
    val end = System.currentTimeMillis()
    println(s"end: $end")
    println(s"time: ${end - start}")
    result
  }

  timeit {
    Thread.sleep(1000)
    println("Hello")
  }

}
