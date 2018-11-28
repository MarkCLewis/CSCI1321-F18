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

  def bubbleSort[A](a: Array[A])(gt: (A, A) => Boolean): Unit = {
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

  def mergesort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = {
    def merge(l1: List[A], l2: List[A]): List[A] = (l1, l2) match {
      case (Nil, _) => l2
      case (_, Nil) => l1
      case (h1 :: t1, h2 :: t2) =>
        if (lt(h1, h2)) h1 :: merge(t1, l2) else h2 :: merge(l1, t2)
    }

    lst match {
      case Nil => Nil
      case h :: Nil => lst
      case _ =>
        val (left, right) = lst.splitAt(lst.length / 2)
        merge(mergesort(left)(lt), mergesort(right)(lt))
    }
  }

  def quicksort[A](lst: List[A])(lt: (A, A) => Boolean): List[A] = lst match {
    case Nil => Nil
    case h :: Nil => lst
    case p :: t =>
      val (less, greater) = t.partition(x => lt(x, p))
      quicksort(less)(lt) ++ List(p) ++ quicksort(greater)(lt)
  }

  def quicksort[A](arr: Array[A])(lt: (A, A) => Boolean): Unit = {
    def helper(start: Int, end: Int): Unit = {
      if (end - start > 1) {
        val p = start + util.Random.nextInt(end - start)
        val tmp = arr(p)
        arr(p) = arr(start)
        arr(start) = tmp
        var low = start + 1
        var high = end - 1
        while (low <= high) {
          if(lt(arr(low), arr(start))) {
            low += 1
          } else {
            val tmp = arr(high)
            arr(high) = arr(low)
            arr(low) = tmp
            high -= 1
          }
        }
        val tmp2 = arr(high)
        arr(high) = arr(start)
        arr(start) = tmp2
        helper(start, high)
        helper(high+1, end)
      }
    }
    helper(0, arr.length)
  }

  val nums = Array.fill(10000000)(util.Random.nextInt(1000000))
  //  val nums = Array.fill(10)(math.random())
  println(nums.take(10).mkString(", "))
  quicksort(nums)(_ < _)
  println(nums.take(10).mkString(", "))

  println(mergesort(List.fill(1000)(util.Random.nextInt(100)))(_ < _))
//  println(quicksort(List.fill(100000)(util.Random.nextInt(100)))(_ < _))
}