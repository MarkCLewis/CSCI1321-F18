package basics

object Sorts extends App {
  def bubbleSortInflexible[A <% Ordered[A]](a: Array[A]): Unit = {
    for (i <- 0 until a.length - 1) {
      for (j <- 0 until a.length - 1 - i) {
        if (a(j) > a(j + 1)) {
          val tmp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = tmp
        }
      }
    }
  }

  def bubbleSort[A](a: Array[A])(gt: (A,A) => Boolean): Unit = {
    for (i <- 0 until a.length - 1) {
      for (j <- 0 until a.length - 1 - i) {
        if (gt(a(j), a(j + 1))) {
          val tmp = a(j)
          a(j) = a(j + 1)
          a(j + 1) = tmp
        }
      }
    }
  }
  
  val nums = Array.fill(10)(util.Random.nextInt(100))
//  val nums = Array.fill(10)(math.random())
  println(nums.mkString(", "))
  bubbleSort(nums)(_>_)
  println(nums.mkString(", "))
}