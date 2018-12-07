package adt

import collection.mutable

class BSTMap[K, V](lt: (K, K) => Boolean) extends mutable.Map[K, V] {
  import BSTMap._

  private var root: Node[K, V] = null

  def get(key: K): Option[V] = {
    var rover = root
    while (rover != null) {
      if (lt(rover.key, key)) rover = rover.right
      else if (lt(key, rover.key)) rover = rover.left
      else return Some(rover.value)
    }
    None
  }

  def inorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
        helper(n.left)
        visitor(n.key, n.value)
        helper(n.right)
      }
    }
    helper(root)
  }

  def preorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
    	  visitor(n.key, n.value)
        helper(n.left)
        helper(n.right)
      }
    }
    helper(root)
  }

  def postorder(visitor: (K, V) => Unit): Unit = {
    def helper(n: Node[K, V]): Unit = {
      if (n != null) {
        helper(n.left)
        helper(n.right)
        visitor(n.key, n.value)
      }
    }
    helper(root)
  }
  
  def numLeaves(): Int = {
    def helper(n: Node[K, V]): Int = {
      if(n == null) 0
      else if(n.left == null && n.right == null) 1
      else {
        helper(n.left)+helper(n.right)
      }
    }
    helper(root)
  }

  def recurSize(): Int = {
    def helper(n: Node[K, V]): Int = {
      if(n == null) 0 else {
        helper(n.left)+helper(n.right)+1
      }
    }
    helper(root)
  }

  def iterator = new Iterator[(K, V)] {
    var stack: List[Node[K, V]] = Nil
    pushAllLeft(root)
    def hasNext: Boolean = stack.nonEmpty 
    def next(): (K, V) = {
      val n = stack.head
      stack = stack.tail
      pushAllLeft(n.right)
      (n.key, n.value)
    }
    def pushAllLeft(n: Node[K, V]): Unit = {
      if(n != null) {
        stack ::= n
        pushAllLeft(n.left)
      }
    }
  }

  def -=(key: K) = {

    this
  }

  def +=(kv: (K, V)): BSTMap.this.type = {
    if (root == null) root = new Node[K, V](kv._1, kv._2, null, null)
    else {
      var rover = root
      var trailer = root
      while (rover != null) {
        trailer = rover
        if (lt(rover.key, kv._1)) rover = rover.right
        else if (lt(kv._1, rover.key)) rover = rover.left
        else {
          rover.value = kv._2
          return this
        }
      }
      if (lt(kv._1, trailer.key)) trailer.left = new Node[K, V](kv._1, kv._2, null, null)
      else trailer.right = new Node[K, V](kv._1, kv._2, null, null)
    }
    this
  }
}

object BSTMap extends App{
  private class Node[K, V](val key: K, var value: V, var left: Node[K, V], var right: Node[K, V]) {
    override def toString() = s"$key, $value"
  }
  
  val map = new BSTMap[Int, String](_<_)
  map += (5 -> "five") += (3 -> "three") += (7 -> "seven")
  map.inorder((k, v) => println(k, v))
  map.preorder((k, v) => println(k, v))
  map.postorder((k, v) => println(k, v))
  println("Iterator")
  map.foreach(println)
}