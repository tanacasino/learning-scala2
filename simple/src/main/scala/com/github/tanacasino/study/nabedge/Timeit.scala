package com.github.tanacasino.study.nabedge

object Timeit extends App {

  def timeIt[A](f:() => A):A = {
    val start: Long = System.currentTimeMillis()
    println(s"start $start")
    val result = f()
    val end: Long = System.currentTimeMillis()
    val time = end - start
    println(s"end $end")
    println(s"time $time")
    result
  }
  
  timeIt ({ () =>
    Thread.sleep(100)
    println("Hello, World !")
  })
  
  /* 
// 実行結果が以下のようになる timeit 関数を作ってみよう
//引数にて関数を受け取ればよいのです！
  timeit {
 Thread.sleep(100)
 println("Hello, World!")
}

"""
start: 12345677890
Hello, World!
end: 12345678100
time: 101
"""

   */
  
  
}
