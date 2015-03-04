package com.github.tanacasino.study.onozawa

import org.joda.time.DateTime

/**
 * Created by kohsaku.onozawa on 15/03/04.
 */
object Inplicit {
  def main(args: Array[String]) {
    implicit class RichInt(val value: Int) {
      def day: String = {
        "d" + value.toString
      }
    }
    implicit class RichDateTime(val value: DateTime) {
      def +(t: String): DateTime = {
        t match {
          case s if s.startsWith("d") => value.plusDays(t.substring(1).toInt)
          case s => new DateTime
        }
      }
    }
    val now = new DateTime
    println(now)
    val tomorrow = now + 1.day
    println(tomorrow)
  }
}