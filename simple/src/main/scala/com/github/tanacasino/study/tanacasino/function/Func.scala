package com.github.tanacasino.study.tanacasino.function


object Func {

  def main (args: Array[String]) {
    //    // 引数 0
    //    val f0: Function0[String] = () => "関数"
    //    val f0_2: () => String = () => "関数"
    //    val f0_3: Function0[String] = new Function0[String] {
    //      def apply() = {
    //        "関数"
    //      }
    //    }
    //
    //    val xx = f0_3.apply()
    //    val yy = f0_3()
    //
    //    // 引数 1
    //    val f1: Function1[Int, String] = (n: Int) => n.toString
    //    val f1_2: Function1[Int, String] = n => n.toString
    //    val f1_3: Function1[Int, String] = new Function1[Int, String] {
    //      def apply(n: Int): String = {
    //        n.toString
    //      }
    //    }
    //
    //
    //    // 引数 2
    //    val f2: Function2[Int, Int, Int] = (x: Int, y: Int) => x + y
    //    val f2_3: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    //      def apply(x: Int, y: Int): Int = {
    //        x + y
    //      }
    //    }
    //
    //
    //    timeit {
    //      Thread.sleep(100)
    //      println("Hello, World!")
    //    }
    //
    //    def timeit(f: => Unit): Unit = {
    //      val start = System.currentTimeMillis()
    //      println(s"start: $start")
    //      f
    //      val end = System.currentTimeMillis()
    //      println(s"end: $end")
    //      println(s"time: ${end - start}")
    //    }
    //
    //
    //
    //    println("curry")
    //    val area314 = areaV2(3.14) _
    //    println(area314(10))
    //    val area3 = areaV2(3.0) _
    //    area3(10)
    //
    //    println("partial apply")
    //    val areaV3_314 = areaV3(3.14, _: Double)
    //    println(areaV3_314(10))
    //
    //
    //
    //    println("--- compose ---")
    //    composeTest
    //  }
    //
    //
    //  def composeTest = {
    //    val f = (x: Int) => x + 2
    //    val g = (x: Int) => x * 2
    //    val h = f compose g
    //    println(f(g(10)))
    //    println(h(10))
    //  }
    //
    //
    //
    //
    //
    //  def areaV2(pi: Double)(radius: Double): Double = {
    //    pi * radius * radius
    //  }
    //
    //  def areaV3(pi: Double, radius: Double): Double = {
    //    pi * radius * radius
    //  }
    //
    //
    //
    functionCompose()

    chain()
    partial()
    maptest()
  }

  def functionCompose() = {
    val f = (x: Int) => x + 1
    val g = (x: Int) => x * 3
    val h1 = f compose g
    val h2 = g andThen f

    val username = "tarou"
    val findUser = (username: String) => 1111122L
    val getTweets = (userId: Long) => List("a", "b", "c")

    val getUserTweets = getTweets compose findUser
    val tweets = getUserTweets(username)

    assert(h1(10) == 31)
    assert(h2(10) == 31)



  }


  def chain() = {
    val f1 = (x: Int) => { x + 1 }
    val f2 = (x: Int) => { x * 2 }
    val f3 = (x: Int) => { x - 1 }

    val f4 = chainFunction(f1, f2, f3)
    println(f4(10)) // => 21
  }

  def chainFunction[T](f: Function[T, T]*) = {
    f.reduce((a, b) => a andThen b)
  }


  def partial() = {
    val expected = Seq("Java", "JavaScript", "Cool")
    val langs = Seq("Java", "JavaScript", "Scala")



    val result = replace(langs, {case "Scala" => "Cool"})
    val result2 = replace2(langs) {
      case "Scala" => "Cool"
    }
    println(result)
    assert(expected == result)

    println(result2)
    assert(expected == result2)
    assert(expected == replace3(langs, {case "Scala" => "Cool"}))
  }

  def replace(seq: Seq[String], pf: PartialFunction[String, String]): Seq[String] = {
    seq.map { x =>
      if(pf.isDefinedAt(x)) pf(x) else x
    }
  }

  def replace2(seq: Seq[String])(pf: PartialFunction[String, String]): Seq[String] = {
    seq.map { x =>
      if(pf.isDefinedAt(x)) pf(x) else x
    }
  }

  def replace3(seq: Seq[String], pf: PartialFunction[String, String]): Seq[String] = {
    seq.map(x => pf applyOrElse(x, (n: String) => n))
    seq.map(x => pf applyOrElse(x, identity[String]))
    seq.map(pf orElse { case x => x })
    seq.collect(pf orElse { case x => x })
  }

  def replace4(seq: Seq[String], pf: PartialFunction[String, String]): Seq[String] = {
    seq.map(pf orElse { case x => x })
  }


  def maptest() = {
    val list = List(1, 2, 3)
    list.map { x =>
      if(x == 1) {
        "one"
      } else if (x == 2) {
        "two"
      } else {
        "other"
      }
    }.foreach(println)
    list.map {
      case 1 => "one"
      case 2 => "two"
      case 3 => "other"
    }.foreach(println)

    list.map(Map(1 -> "one", 2 -> "two") orElse { case _ => "other" }).foreach(println)

  }

}
