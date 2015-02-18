package com.github.tanacasino.study.kitaly.jad

/**
 * Created by kitagawa on 15/02/02.
 */
class LazySingleton {
  println("Lazy Construct")
  val name = "SINGLETON"
}


object LazySingleton {
  private lazy val instance = new LazySingleton
  def apply() = instance
}