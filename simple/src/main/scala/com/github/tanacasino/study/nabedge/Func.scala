package com.github.tanacasino.study.nabedge

import scala.util.Try

object Func extends App {

  //関数の合成
  def functionCompose() = {
    val f = (x: Int) => x + 1
    val g = (x: Int) => x * 3
    val h1 = f compose g
    val h2 = g andThen f
    assert(h1(10) == 31)
    assert(h2(10) == 31)
  }
  functionCompose()


  //任意の数の関数を受け取り
  //それらを順に適用する関数を返す関数
  def chainFunction(funcs: Function[Int,Int]*) = {
    funcs.reduceLeft(_ andThen _)
  }
  val f1 = (x: Int) => { x + 1 }
  val f2 = (x: Int) => { x * 2 }
  val f3 = (x: Int) => { x - 1 }
  val f4 = chainFunction(f1, f2, f3)
  println(f4(10)) // => 21 ならok

  /*
  Seq[String]とPartialFunctionを受け取り、
  各要素に対してPartialFunctionにマッチした場合はその戻り値、
  マッチしない場合は元の要素を格納したSeqを返す関数（以下の例のreplace関数）を作ってみよう。
   */
  def replace(langs: Seq[String], pf: PartialFunction[String,String]) = {
//    langs.map{ lang => Try(pf(lang)).getOrElse(lang) }.toSeq
//    langs.map{ lang => if (pf.isDefinedAt(lang)) pf(lang) else lang}.toSeq
  }
  val langs = Seq("Java", "JavaScript", "Scala")
  val result = replace(langs, { case "Scala" => "Cool" })
  println(result) // => Seq(Java, JavaScript, Cool)になったらOK!
}
