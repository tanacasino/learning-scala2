package com.githu.tanacasino.study.tanacasino.implicits
import org.joda.time._

object Conversion {

  implicit class RichDateTime(dt: DateTime) { 
    def +(period: Period): DateTime = dt.plus(period)
    def -(period: Period): DateTime = dt.minus(period)
  }

  implicit class RichPeriod(i: Int) {
    def day: Period = Period.days(i)
  }

  def main(args: Array[String]) {

    val now = new DateTime
    println(now)
    val tomorrow = now + 1.day
    println(tomorrow)

  }


}
