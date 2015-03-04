package com.githu.tanacasino.study.tanacasino.implicits
import org.joda.time.DateTime

object Conversion {



  implicit class MyInt(val value: Int) {
    def hoge() = (1 to value).foreach(_ => println("hoge"))

  }

  implicit class RichDateTime(val vlue: DateTime) {



  }

  def main(args: Array[String]) {

//    println(twice(MyInt(2)))
//    println(twice("2"))

    //2.hoge()

    val now = new DateTime
    val tomorrow = now + 1.day


  }


//  implicit  def string2Int(s: String): MyInt = MyInt(s.toInt)

//  implicit def int2MyInt(i: Int): MyInt - MyInt(i)



}
