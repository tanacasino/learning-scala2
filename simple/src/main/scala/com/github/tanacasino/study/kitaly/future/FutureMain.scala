package com.github.tanacasino.study.kitaly.future

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * Created by kitagawa on 15/01/27.
 */
object FutureMain1 extends Future1 {

  def main(args: Array[String]): Unit = {
//    futureIntro()
//    future1()
//    future2()
//    future3()
  }
}

trait Future1 {


  /**
   * Recover & RecoverWith
   */
  def future3(): Unit = {
    val f1 = Future("Hello").filter(_.size < 5).recover {
      case t => "Recovery"
    }

    f1.onComplete{
      case Success(value) => println(s"f1 success: $value")
      case Failure(error) => println(s"f1 failure: $error")
    }
    
    val recover = Future("Recovery")
    val f2 = Future("Hello").filter(_.size < 5).recoverWith {
      case t:Throwable => recover
    } 
    
    f2.onComplete{
      case Success(value) => println(s"f2 success: $value")
      case Failure(error) => println(s"f2 failure: $error")
    }
    
    Thread.sleep(2000)
  }

  /**
   * Collect (map + filter)
   */
  def future2(): Unit = {
    val f = Future("Hello").collect {
      case s if s.size < 2 => s.size
//      case _ => "Default"
    }

    f.onComplete {
      case Success(value) => println(s"f success: $value")
      case Failure(error) => println(s"f failure: $error")
    }

    Thread.sleep(2000)
  }

  /**
   * Map & Filter
   */
  def future1(): Unit = {
    
    val f1  = Future("Hello").map(_.size)
    
    f1.onComplete {
      case Success(value) => println(s"f1 success: $value")
      case Failure(error) => println(s"f1 failure: $error")
    }
    
    val f2 = Future("Hell").filter(_.size < 5)

    f2.onComplete {
      case Success(value) => println(s"f2 success: $value")
      case Failure(error) => println(s"f2 failure: $error")
    }

    Thread.sleep(2000)
  }


  /**
   * Future Intro
   */
  def futureIntro(): Unit = {
    println("Hello World!")

    val f = Future[String] {
      Thread.sleep(5000)
      "future"
      //      throw new Exception("Error!")
    }

    val f2 = Future[String] {
      "future2"
    }

    //    f.onComplete {
    //      case Success(value) => println(s"onComplete: $value")
    //      case Failure(error) => println(s"onComplete: $error")
    //    }

    f.onSuccess {
      case value: String =>
        println(s"onSuccess1: $value")
    }
    f.onSuccess {
      case value: String =>
        println(s"onSuccess2: $value")
    }
    f.onSuccess {
      case value: String =>
        println(s"onSuccess3: $value")
    }
    f.onFailure{
      case error: Throwable => println(s"onFailure $error")
    }

    println("End")
    Thread.sleep(6000)
    println("end...")
  }
  
}