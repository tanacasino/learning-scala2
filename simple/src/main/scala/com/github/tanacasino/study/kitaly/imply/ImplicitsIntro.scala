package com.github.tanacasino.study.kitaly.imply

/**
 * Created by kitagawa on 15/03/04.
 */
object ImplicitsIntro {

  implicit class MyInt(val value: Int) {
    def hoge() = (1 to value).foreach(_ => println("hoge"))
  }

//  case class MyInt(val value: Int) {
//    def hoge() = (1 to value).foreach(_ => println("hoge"))
//  }
  
//  def twice(i: MyInt): Int = i.value * 2
//  implicit  def string2MyInt(s: String): MyInt = MyInt(s.toInt)
//  implicit def int2MyInt(i: Int): MyInt = MyInt(i)
  
  def main(args: Array[String]): Unit = {
//    println(twice(MyInt(2)))
//    println(twice("2"))
    
    4 hoge
    
    new MyInt(4).hoge()
  }

}
