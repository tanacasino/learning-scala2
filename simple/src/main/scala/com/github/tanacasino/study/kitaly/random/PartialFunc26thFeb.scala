package com.github.tanacasino.study.kitaly.random

/**
 * Created by kitagawa on 15/02/26.
 */
object PartialFunc26thFeb {


  
  def main(args: Array[String]) {
    val langs = Seq("Java", "JavaScript", "Scala")

    def replace(seq: Seq[String], pf: PartialFunction[String, String]): Seq[String] = {
      seq.map{
        case s if pf.isDefinedAt(s) => pf.apply(s)
        case s => s
      }
    }
    
    val result = replace(langs, { case "Scala" => "Cool"})
    println(result)
  }

}
