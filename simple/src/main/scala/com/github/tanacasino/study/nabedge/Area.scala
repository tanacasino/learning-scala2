package com.github.tanacasino.study.nabedge

object 
Area extends App {

  // area関数は引数として円周率を受け取り、その円周率を使って面積を求める関数を返す。その関数は半径を受け取る

  val f1 = area(3.14)
  println(f1(10)) // 314.0
  println(f1(1)) // 3.14
  val f2 = area(3)
  println(f2(10)) // 300
  println(f2(1)) // 3

  def area(pi: Double): (Double) => Double = {
    (r: Double) => r * r * pi
  }

}
