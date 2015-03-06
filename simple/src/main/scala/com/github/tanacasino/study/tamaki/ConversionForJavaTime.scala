package com.github.tanacasino.study.tamaki

import java.time._

/**
 * Created by shintaro.tamaki on 2015/03/06.
 */
object ConversionForJavaTime {

  // java.timeでやってみました。
  implicit class RichLocalDateTime(dt: LocalDateTime) {
    def +(period: Period): LocalDateTime = dt.plus(period)
    def -(period: Period): LocalDateTime = dt.minus(period)
    def +(duration: Duration): LocalDateTime = dt.plus(duration)
    def -(duration: Duration): LocalDateTime = dt.minus(duration)
    def nextDay: LocalDateTime = dt.plus(1.day)
    def nextMonth: LocalDateTime = dt.plus(1.month)
  }

  implicit class RichInt(i: Int) {
    def nanoSecond: Duration = Duration.ofNanos(i)
    def milliSecond: Duration = Duration.ofMillis(i)
    def second: Duration = Duration.ofSeconds(i)
    def minute: Duration = Duration.ofMinutes(i)
    def hour: Duration = Duration.ofHours(i)
    def day: Period = Period.ofDays(i)
    def month: Period = Period.ofMonths(i)
    def year: Period = Period.ofYears(i)
    def nextDay: Period = Period.ofDays(1)
  }

  def main(args: Array[String]) {
    val now = LocalDateTime.now()
    println(now)
    val tomorrow = now + 1.day
    println(tomorrow)
    println(now - 1.day)
    println(now - 1.minute)
    println(now - 6.hour)
    println(now.nextDay)
  }
}
