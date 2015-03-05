package com.github.tanacasino.study.koga.implicits

import org.joda.time.DateTime

object Main {

  def main(args: Array[String]) {
    val now = new DateTime()
    val tomorrow = now + 1.day

    println(now)
    println(tomorrow)
  }

  implicit class RichInt(val i: Int) {
    def day() = i * 60 * 60 * 24 * 1000L
  }

  implicit class RichDateTime(val datetime: DateTime) {
    def +(duration: Long) = datetime.plus(duration)
  }
}

