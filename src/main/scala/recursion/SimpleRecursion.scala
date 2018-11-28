package recursion

object SimpleRecursion extends App {
  def fib(n: Int): Int = if(n<2) 1 else fib(n-1)+fib(n-2)
  def fact(n: Int): Int = if(n<2) 1 else n*fact(n-1)
  
  println(fib(6))
  println(fact(5))
  
  def packBins(bins: Array[Int], objs: List[Int]): Boolean = {
    if(objs.isEmpty) true else {
      val o = objs.head
      val rest = objs.tail
      var i = 0
      var itFit = false
      while(i < bins.length && !itFit) {
        if(bins(i) >= o) {
          bins(i) -= o
          if(packBins(bins, rest)) itFit = true
          bins(i) += o
        }
        i += 1
      }
      itFit
    }
  }
  
  println(packBins(Array(7,5), List(4, 4, 4)))
}