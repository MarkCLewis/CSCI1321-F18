package adt

import scala.reflect.ClassTag

class SAPriorityQueue[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var arr = new Array[A](10)
  private var numElems = 0

  def dequeue(): A = {
    val ret = arr(0)
    for (i <- 1 to numElems) {
      arr(i - 1) = arr(i)
    }
    numElems -= 1
    ret
  }
  def enqueue(a: A): Unit = {
    if (numElems + 1 > arr.size) {    
      val copy = new Array[A](arr.size*2)
      for(i <- 0 until numElems) copy(i) = arr(i)
      arr = copy
    }
    var index = -1
    for (i <- 0 until numElems) {
      if (higherP(arr(i), a) && higherP(a, arr(i + 1))) index = i + 1
    }
    for(i <- numElems to index+1 by -1) {
      arr(i) = arr(i-1)
    }
    arr(index) = a
    numElems += 1

  }
  def isEmpty: Boolean = numElems == 0

  def peek: A = arr(0)
}
