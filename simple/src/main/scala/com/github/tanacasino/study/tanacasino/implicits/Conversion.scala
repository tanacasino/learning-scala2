package com.githu.tanacasino.study.tanacasino.implicits
import org.joda.time._

object Conversion {

  implicit class RichDateTime(dt: DateTime) { 
    def +(period: Period): DateTime = dt.plus(period)
    def -(period: Period): DateTime = dt.minus(period)
    def nextDay: DateTime = dt.plus(1.day)
    def nextMonth: DateTime = dt.plus(1.month)
  }

  implicit class RichPeriod(i: Int) {
    def day: Period = Period.days(i)
    def minute: Period = Period.minutes(i)
    def hour: Period = Period.hours(i)
    def month: Period = Period.months(i)
    def nextDay: Period = Period.months(1)

  }

  def main(args: Array[String]) {

    val now = new DateTime
    println(now)
    val tomorrow = now + 1.day
    println(tomorrow)
    println(now - 1.day)
    println(now - 1.minute)
    println(now - 6.hour)
    println(now.nextDay)

  }

}
