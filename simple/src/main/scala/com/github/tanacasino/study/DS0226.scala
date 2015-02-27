package com.github.tanacasino.study

/**
 * Created by kenichi.saito on 2/26/15.
 */
object DS0226 extends App {


  println("test")


  val langs = Seq("Java", "JavaScript", "Scala")
  val result = replace(langs, { case "Scala" => "Cool" })
  println(result) // => Seq(Java, JavaScript, Cool)


  //Seq[String]とPartialFunctionを受け取り、各要素に対してPartialFunctionにマッチした場合はその戻り値、
  // マッチしない場合は元の要素を格納したSeqを返す関数（以下の例のreplace関数）を作ってみよう。

  def replace(s: Seq[String], pf: PartialFunction[String,String]) :Seq[String]= {

    s.map(se =>  if (pf.isDefinedAt(se)) pf(se) else se)



    //(pf.isDefinedAt("foo"))

  }
}
