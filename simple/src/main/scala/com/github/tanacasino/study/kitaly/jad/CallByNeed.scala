package com.github.tanacasino.study.kitaly.jad

/**
 * Created by kitagawa on 15/02/02.
 */
class CallByNeed {
  def main(): Unit = callByNeed(heavyFunc)
  
  def callByNeed(msg: => String): String = {
    msg + "hoge"
  }
  
  def heavyFunc: String = "hoge"
}
