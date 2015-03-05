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
//    future4()
//    future7()
//    future11()
    future12()
  }
}

trait Future1 {

  def future12(): Unit = {
    def post(endpoint: String): Future[Either[Throwable, String]] = {
      println(s"post to $endpoint")
      val randInt = Math.round(Math.random() * 5)
      Thread.sleep(randInt * 1000)
      Future.successful(Right("Ok"))
    }
    
    val server = "localhost"
    val cluster1 = Seq("server1", "server2")
    val cluster2 = Seq("server3", "server4")
    
    Seq(cluster1, cluster2).foreach { cluster => 
      val restore = cluster.map { server =>
        post(s"http://$server/restore")
      }
      val restoreDone = Future.sequence(restore)
      
      val activate = restoreDone.map {results => 
        cluster.map { server =>
          post(s"http://$server/activate")
        }
      }
    }
    Thread.sleep(10000)
  }
  
  def future11(): Unit = {
//    val result = Seq("rikunabi", "mynavi", "en")
//      .map { id => Future(aggregate(id)) }
//      .sum
    
    val result = Future
      .reduce(
        Seq("rikunabi", "mynavi", "en")
          .map(id => Future(aggregate(id)))
      )(_ + _)
    
    result.onSuccess{
      case x: Int => println(x)
    }

    
    Thread.sleep(3000)
  }

  private def aggregate(id: String): Int = {
    Thread.sleep(500)
    id.size
  }


  //TODO(kitaly) やってくる
  def future8(): Unit = {
//    val futures = for {
//      i <- List(1,2,3,4,5)
//      f <- Future(i)
//    } yield {
//      f
//    }
  }
  
  def future7(): Unit = {
    val futures:List[Future[Either[Throwable, Int]]] = List(1, 2, 3, 4, 5).map { i =>
      Future {
        if(i % 2 == 0) throw new Exception("Error")
        else Right(i)
      }.recover{
        case t:Throwable => Left(t)
      }
    }
    
    val future = Future.sequence(futures);
    
    future.onSuccess{ 
      case results =>
        results.foreach {
          case Right(v) => println(v)
          case Left(e) => println(e.getMessage)
        }
    }
    
    Thread.sleep(2000)
  }
  
  /**
   * Failure[Throwable] を Future[Throwable] にする
   */
  def future4(): Unit = {
    val f = Future("Hello").filter(_.size <  5)
    f.failed foreach println
    Thread.sleep(1000)
  }

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