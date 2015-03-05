package com.github.tanacasino.study.kitaly.jad

/**
 * Created by kitagawa on 15/02/02.
 */
class CallStraight {
  def main(): Unit = callStraight(heavyFunc)

  def callStraight(msg:String): String = {
    msg + "hoge"
  }

  def heavyFunc: String = "hoge"
}
