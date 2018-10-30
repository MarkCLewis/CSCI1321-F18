package adt

import scala.collection.mutable.Buffer

class SLLBuffer[A] extends Buffer[A] {
  import SLLBuffer.Node

  private var hd: Node[A] = null
  private var numElems = 0

  def +=(elem: A) = {
    if (hd == null) {
      hd = new Node[A](elem, null)
    } else {
      var rover = hd
      while (rover.next != null) rover = rover.next
      rover.next = new Node[A](elem, null)
    }
    numElems += 1
    this
  }
  def +=:(elem: A) = {
    hd = new Node[A](elem, hd)
    numElems += 1
    this
  }
  def apply(n: Int): A = {
    require(n < length && n >= 0)
    var rover = hd
    for (_ <- 0 until n) rover = rover.next
    rover.data
  }
  def clear(): Unit = {
    hd = null
    numElems = 0
  }
  def insertAll(n: Int, elems: Traversable[A]): Unit = {
    require(n <= length && n >= 0)
    var rover: Node[A] = null
    if (n != 0) {
      rover = hd
      for (_ <- 0 until n - 1) rover = rover.next
    }
    for (e <- elems) {
      if (rover == null) {
        hd = new Node(e, hd)
        rover = hd
      } else {
        rover.next = new Node(e, rover.next)
        rover = rover.next
      }
      numElems += 1
    }
  }
  def length: Int = numElems
  def remove(n: Int): A = {
    require(n < length && n >= 0)
    numElems -= 1
    if (n == 0) {
      val ret = hd.data
      hd = hd.next
      ret
    } else {
      var rover = hd
      for (_ <- 0 until n - 1) rover = rover.next
      val ret = rover.data
      rover.next = rover.next.next
      ret
    }
  }
  def update(n: Int, newElem: A): Unit = {
    require(n < length && n >= 0)
    var rover = hd
    for (_ <- 0 until n) rover = rover.next
    rover.data = newElem
  }

  // Members declared in scala.collection.IterableLike
  def iterator: Iterator[A] = new Iterator[A] {
    private var rover = hd
    def hasNext: Boolean = rover != null 
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }
}

object SLLBuffer {
  private class Node[A](var data: A, var next: Node[A])
}