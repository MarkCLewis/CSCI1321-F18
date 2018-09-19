package adt

import scala.reflect.ClassTag

class ArrayQueue[A: ClassTag] extends MyQueue[A] {
  private var data = new Array[A](10) //Array.fill[A](10)(null.asInstanceOf[A])
  private var front = 0
  private var back = 0
  
  def enqueue(a: A): Unit = {
    if((back+1)%data.length == front) {
      val tmp = new Array[A](data.length*2)
      for(i <- 0 until data.length-1) {
        tmp(i) = data((front+i)%data.length)
      }
      data = tmp
    }
    data(back) = a
    back = (back+1)%data.length
  }
  
  def dequeue(): A = {
    val ret = data(front)
    front = (front+1)%data.length
    ret
  }
  
  def peek: A = data(front)
  
  def isEmpty: Boolean = front == back
}