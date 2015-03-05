package com.github.tanacasino.study.kitaly.imply

import org.joda.time.{Period, DateTime}

/**
 * Created by kitagawa on 15/03/04.
 *
 * 参考
 * - joda-time wrapper
 * - nscala time
 *  
 * HW(1) 
 * - add joda time library dependency in build.sbt
 * - val now = new DateTime
 * - val tomorrow = now + 1.day
 * 
 * HW(2)　
 * - now - 6.hours
 * - minute/minutes
 * - day/days
 * - hour/hours
 * - year/years
 * - month/months
 * - next
 * - nextDay
 * - nextHour
 * - nextYear
 * - プラスアルファ！ now = 2 day - 1 day - 2 day とか、なんでも
 */
object RichDate {

  def main(args: Array[String]) {
    
    implicit class RichPeriod(int: Int) {
      def day = Period.days(int)
    }
    
    implicit class RichDate(val date: DateTime) {
      def +(period: Period): DateTime = date.plus(period)
    }
    
    val now = new DateTime()
    val tomorrow = now + 1.day
    
    println(tomorrow)
  }
  
}
