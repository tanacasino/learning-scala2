package com.github.tanacasino.study.tanacasino.future


import scala.compat.Platform
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
    //future7()

    //future11()
    //future11_2()
    future12()


    // めんどいのでSleepしてますが、本当はAwaitですね
    Thread.sleep(3000)
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

    Platform.currentTime

    val f = Future.sequence(Seq(f1, f2, f3))

    f.onSuccess {
      case values => values.foreach(println)
    }

    Await.ready(f, Duration.Inf)
    Thread.sleep(1000)
    println("done")
  }




  def future11() = {
    // なんか重い処理
    def aggregate(id: String): Int = {
      Thread.sleep(500)
      // http request
      id.size
    }

    val result = Seq("rikunavi", "mynavi", "en")
                  .map(id => aggregate(id))
                  .sum
    println(result)

    val result2 = Seq("rikunavi", "mynavi", "en")
      .map(id => Future(aggregate(id)))

    var sum = 0
    result2.foreach { f =>
      val x = Await.result(f, Duration.Inf)
      sum += x
    }
    //Await.result(result2, Duration.Inf)

    val result3 = Future
      .reduce(
        Seq("rikunavi", "mynavi", "en")
          .map(id => Future(aggregate(id)))
      )(_ + _)

    val func: (Int, Int) => Int = (a, b) => a + b
    List(1,2,3).reduce(_ + _)
    List(1,2,3).reduce(func)
  }




  def future12() = {

    def post(endpoint: String): Future[Either[Throwable, String]] = {
      println(s"post to $endpoint")
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
      restoreDone.foreach { results =>
        cluster.foreach { server =>
          post(s"http://$server/activate")
        }
      }
    }


    Seq(cluster1, cluster2).foreach { cluster =>
      // restoreリクエストを送信
      cluster.map { server =>
        post(s"http://${server}/restore")
      }.map(Await.result(_, Duration.Inf)) // 待ち合わせ

      // activateリクエストを送信
      cluster.foreach { server =>
        post(s"http://${server}/activate")
      }
    }


    println("----------------")
    Thread.sleep(2000)
    println("----------------")


    Seq(cluster1, cluster2).foreach { cluster =>
      Future.sequence(
        // restoreリクエストを送信
        cluster.map { server =>
          post(s"http://${server}/restore")
        }
      ).map { _ =>
        // activateリクエストを送信
        cluster.map { server =>
          post(s"http://${server}/activate")
        }
      }
    }
  }
}

