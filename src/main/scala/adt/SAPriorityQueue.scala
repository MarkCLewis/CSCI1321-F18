package adt

import scala.reflect.ClassTag

class SAPriorityQueue[A: ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var arr = new Array[A](10)
  private var numElems = 0

  def dequeue(): A = {
	  numElems -= 1
    arr(numElems)
  }
  def enqueue(a: A): Unit = {
    if (numElems + 1 > arr.size) {    
      val copy = new Array[A](arr.size*2)
      for(i <- 0 until numElems) copy(i) = arr(i)
      arr = copy
    }
    var i = numElems-1
    while(i >= 0 && higherP(arr(i), a)) {
      arr(i+1) = arr(i)
      i -= 1
    }
    arr(i+1) = a
    numElems += 1
  }
  def isEmpty: Boolean = numElems == 0

  def peek: A = arr(0)
}
