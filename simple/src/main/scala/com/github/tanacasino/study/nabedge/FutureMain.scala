package com.github.tanacasino.study.nabedge

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureMain extends FutureTrait {

  def main(args: Array[String]): Unit = {
//    future2()
    drill01()
  }

}

trait FutureTrait {

  // ただのスリープ
  def future1() = {
    println("Start")
    Thread.sleep(5000)
    println("end")
  }

  // Futureによる非同期計算
  def future2() = {
    println("Start")
    val f = Future {
      Thread.sleep(5000)
      println("Future!!!")
    }
    println("end")
    Thread.sleep(6000)
  }

  def drill01() = {
    val f1 = Future("Hello").map(_.size)
    f1.onComplete{
      case Success(h) => println(s"f1 success $h")
      case Failure(e) => println(s"f1 failed: e")
    }
    Thread.sleep(5000)
    println("*** drill01 END ***")
//    val newFuture = Future("new future")
//    val f2 = Future("Helloaa").filter(_.size < 5).recoverWith{
//      case e: NoSuchElementException => newFuture
//    }
  }
}