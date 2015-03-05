package com.github.tanacasino.study.kitaly.imply

import org.joda.time.{ReadablePeriod, Period, DateTime}

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
 * - プラスアルファ！
 */
object RichDate {

  def main(args: Array[String]) {

    implicit class RichPeriod(int: Int) {
      def year = Period.years(int)
      def month = Period.months(int)
      def day = Period.days(int)
      def hour = Period.hours(int)
      def minute = Period.minutes(int)
    }

    implicit class RichDate(val date: DateTime) {
      def +(period: ReadablePeriod): DateTime = date.plus(period)
      def -(period: ReadablePeriod): DateTime = date.minus(period)

      def nextYear: DateTime = date.plus(1.year)
      def nextMonth: DateTime = date.plus(1.month)
      def nextDay: DateTime = date.plus(1.day)
      def nextHour: DateTime = date.plus(1.hour)
      def nextMinute: DateTime = date.plus(1.minute)

      def isGreaterThan(dt: DateTime) = date.getMillis > dt.getMillis
      def isLessThan(dt: DateTime) = date.getMillis < dt.getMillis
      def isLessEqual(dt: DateTime) = date.getMillis <= dt.getMillis
      def isGreaterEqual(dt: DateTime) = date.getMillis >= dt.getMillis
    }
    
    val now = new DateTime()
    println(now)

    val nextYear: DateTime = now + 1.year
    val lastYear: DateTime = now - 1.year
    println(nextYear)
    println(lastYear)
    println(nextYear isGreaterThan lastYear)

    val nextMonth: DateTime = now + 1.month
    val lastMonth: DateTime = now - 1.month
    println(nextMonth)
    println(lastMonth)
    println(nextMonth isGreaterEqual lastMonth)

    val tomorrow: DateTime = now + 1.day
    val yesterday: DateTime = now - 1.day
    println(tomorrow)
    println(yesterday)
    println(yesterday isLessEqual tomorrow)

    val nextHour: DateTime = now + 1.hour
    val lastHour: DateTime = now - 1.hour
    println(nextHour)
    println(lastHour)
    println(lastHour isLessThan nextHour)

    println(now + 1.minute)
    println(now - 1.minute)

    println(now nextYear)
    println(now nextMonth)
    println(now nextDay)
    println(now nextHour)
    println(now nextMinute)

    println((now nextYear) - 1.day)
  }
  
}
