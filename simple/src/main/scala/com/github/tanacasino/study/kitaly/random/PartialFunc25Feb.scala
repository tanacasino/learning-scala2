package com.github.tanacasino.study.kitaly.random

/**
 * Created by kitagawa on 15/02/25.
 */
object PartialFunc25Feb {
  
  def main(args: Array[String]) {
    
//    compoundFunc
    chaingFuncHw

  }
  
  def partial = {
    def areaV3(pi: Double, radius: Double): Double = {
      pi * radius * radius
    }

    val areaV3_314 = areaV3(3.14, _: Double)

    println(areaV3_314(10))
    
  }
  
  def curry = {

    def areaV2(pi: Double)(radius: Double): Double = {
      pi * radius * radius
    }


    val area314 = areaV2(3.14) _
    println(area314(10))


    val area3 = areaV2(3.0) _
    area3(10)
  }
  
  def compoundFunc = {
    val f = (x: Int) => x + 1
    val g = (x: Int) => x * 3
    val h1 = f compose g
    val h2 = g andThen f


    assert(h1(10) == 31)
    assert(h2(10) == 31)
    
    val userName = "Taro"
    val findUser = (username: String) => 111111122L
    val getTweets = (userId: Long) => List("a", "b", "c")

    val getUserTweets = findUser andThen getTweets
    println(getUserTweets(userName))
  }
  
  def chaingFuncHw = {
    val f1 = (x: Int) => {x + 1}
    val f2 = (x: Int) => {x * 2}
    val f3 = (x: Int) => {x - 1}


    def chainFunction (funcs: Function1[Int, Int]*) = {
      funcs.reduceLeft(_ andThen _)
    }
    
    val f4 = chainFunction(f1, f2, f3)
    println(f4(10))
  }
  
}
