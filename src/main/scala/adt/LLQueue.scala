package adt

class LLQueue[A] extends MyQueue[A] {
  import LLQueue.Node
  private var front, back: Node[A] = null

  def enqueue(a: A): Unit = {
    if (isEmpty) {
      front = new Node[A](a, null)
      back = front
    } else {
      back.next = new Node[A](a, null)
      back = back.next
    }
  }

  def dequeue(): A = {
    val ret = front.data
    front = front.next
    ret
  }

  def peek: A = front.data

  def isEmpty: Boolean = front == null
}

object LLQueue {
  private class Node[A](val data: A, var next: Node[A])
}