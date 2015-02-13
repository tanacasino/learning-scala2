package com.github.tanacasino.study.tanacasino.future


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Await, Future}
import scala.util.{Failure, Success}


object FutureMain1 extends Future1 {

  def main(args: Array[String]): Unit = {
    //future1()
    //future2()
    //future3()
    //future4()
    //future5()
    //future6()
    future7()
  }

}


trait Future1 {

  // ただのスリープ
  def future1() = {
    println("Start")

    Thread.sleep(5000)

    println("end")
  }


  // Futureによる非同期計算
  def future2() = {
    println("Start")
//    object MyObject {
//      def apply[T](f: => T)(implicit val ec: ExecutionContext): T = {
//        f
//      }
//    }
    val f = Future {
      Thread.sleep(5000)
      println("Future!!!")
    }

    println("end")
    Thread.sleep(6000)
  }



  // Futureとコールバック
  def future3() = {
    println("Start")

    val f = Future {
      Thread.sleep(5000)
      "future"
    }

    f.onComplete {
      case Success(value) =>
        println(s"Completed! : $value")
      case Failure(error) =>
        println(error)
    }

    println("end")
    Thread.sleep(6000)
  }

  // Futureとコールバック
  def future4() = {
    println("Start")

    val f = Future {
      Thread.sleep(5000)
      "future"
    }

    f.onSuccess {
      case value: String => println(s"Success ! : $value")
    }

    println("end")
    Thread.sleep(6000)
  }


  // Futureとコールバック
  def future5() = {
    println("Start")

    val f = Future {
      Thread.sleep(5000)
      throw new Exception("やばい！")

    }

    f.onFailure {
      case error: Throwable => println(s"Fail!: ${error.getMessage}")
    }

    println("end")
    Thread.sleep(6000)
  }


  // Futureとコールバック 複数コールバック
  // 順序保証はされない
  def future6() = {
    println("Start")

    val f = Future {
      Thread.sleep(5000)
      "future"
    }

    f.onComplete {
      case Success(value) =>
        println(s"Completed 1 : $value")
      case Failure(error) =>
        println(error)
    }

    f.onComplete {
      case Success(value) =>
        println(s"Completed 2 : $value")
      case Failure(error) =>
        println(error)
    }

    println("end")
    Thread.sleep(6000)
  }



  /*
   *
   * コンビネータ編に続く
   *
   */


  // 複数のFutureの結果を待ち合わせる
  def future7() = {
    val f1 = Future { "future1" }
    val f2 = Future { "future2" }
    val f3 = Future { "future3" }

    val f = Future.sequence(Seq(f1, f2, f3))

    f.onSuccess {
      case values => values.foreach(println)
    }

    Await.ready(f, Duration.Inf)
    Thread.sleep(1000)
    println("done")
  }
}

