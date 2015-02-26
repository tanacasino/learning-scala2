package com.github.tanacasino.study.koga.function

/**
 * Created by tomoki.koga on 15/02/26.
 */
object Chain {

  def main(args: Array[String]) {
    functionCompose()
  }

  def functionCompose() = {
    val f1 = (x: Int) => x + 1
    val f2 = (x: Int) => x * 2
    val f3 = (x: Int) => x - 1

    val f4 = chainFunction(f1, f2, f3)
    assert(f4(10) == 21)
  }

  def chainFunction(f: ((Int) => Int)*) = f.reduce(_ andThen _)

}
