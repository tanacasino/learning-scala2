package com.githu.tanacasino.study.tanacasino.implicits
import org.joda.time._

object Conversion {

  class RichDateTime(dt: DateTime) { 
    def +(period: Period): DateTime = dt.plus(period)
    def -(period: Period): DateTime = dt.minus(period)
  }

  implicit def convertDateTime(dt: DateTime)  = new RichDateTime(dt)
  
  class RichPeriod(i: Int) {
    def day: Period = i match {
      case i: Int => Period.days(i)
      case _ => throw new Exception
    }
    def days: Period = Period.days(i)
  }

  implicit def convertPeriod(i: Int) = new RichPeriod(i)

  def main(args: Array[String]) {

    val now = new DateTime
    println(now)
    val tomorrow = now + 1.day
    println(tomorrow)

  }


}
