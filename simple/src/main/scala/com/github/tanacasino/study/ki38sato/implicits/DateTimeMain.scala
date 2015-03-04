package com.github.tanacasino.study.ki38sato.implicits

import org.joda.time.DateTime

/**
 * Created by kimiya.sato on 2015/03/04.
 */
object DateTimeMain {
  implicit class MyInt(value: Int) {
    def day(): Int = value
  }

  implicit class MyDateTime(value: DateTime) {
    def +(i: Int): DateTime = value.plusDays(i)
  }

  def main(args: Array[String]) {
    val now = new DateTime()
    val tommorow = now + 1.day
    println(tommorow)
  }
}
