package com.github.tanacasino.study.kitaly.future

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
 * Created by kitagawa on 15/01/27.
 */
object FutureMain {
  def main(args: Array[String]) {
    println("Hello World!")
    
    val f = Future[String] {
      Thread.sleep(5000)
      "future"
//      throw new Exception("Error!")
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
