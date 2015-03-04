import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

object FutureMain extends App {

}

trait Future1 {
  def future1() = {
    println("start")

    Thread.sleep(5000)

    println("end")
  }

  def future2() = {
    println("start")

    val f = Future {
      Thread.sleep(5000)
      println("Future!")
    }

    println("end")

    Thread.sleep(5000)
  }

  def future3() = {
    println("start")

    val f = Future {

    }

  }
}