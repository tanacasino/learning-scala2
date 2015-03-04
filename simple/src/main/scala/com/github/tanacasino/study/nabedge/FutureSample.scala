package com.github.tanacasino.study.nabedge

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/*
以下のような集計処理を行うメソッドがあるとします。
def aggregate(id: String): Int ={
...
}
このメソッドを使って特定のIDに対する集計結果の合計を求めるコードがあります。
val result = Seq("rikunabi", "mynavi", "en").map { id => aggregate(id) }.sum
ただし、aggregateメソッドは実行に時間がかかるので並列化したいと思います。Futureを使って書き直してみましょう。

 */
object FutureSample {

  def aggregate(id: String): Int ={
    Thread.sleep(100) // なんか重い処理
    id.size
  }

  def main(args: Array[String]): Unit = {
//    val result = Seq("rikunabi", "mynavi", "en").map { id => aggregate(id) }.sum
//    println(result)
    val result2 = Seq("rikunabi", "mynavi", "en").map(id => Future(aggregate(id)))
    val f = Future.reduce(result2)(_ + _)
    f.foreach(println)
    Thread.sleep(1000)
//    println(result2)
  }
}

