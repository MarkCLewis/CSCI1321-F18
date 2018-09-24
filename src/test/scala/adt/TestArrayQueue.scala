package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class TestArrayQueue {
  private var queue: MyQueue[Int] = null
  
  @Before def init: Unit = {
    queue = new ArrayQueue[Int]()
  }
  
  @Test def startsEmpty: Unit = {
    assertTrue(queue.isEmpty)
  }
  
  @Test def enqueuOne: Unit = {
    queue.enqueue(5)
    assertFalse(queue.isEmpty)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue)
    assertTrue(queue.isEmpty)
  }
  
  @Test def enqueueTwo: Unit = {
    queue.enqueue(5)
    queue.enqueue(3)
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue)
    assertEquals(3, queue.peek)
    assertEquals(3, queue.dequeue)
  }
  
  @Test def untilGrow: Unit = {
    val nums = Array.fill(15)(util.Random.nextInt(100))
    for(i <- nums) queue.enqueue(i)
    for(i <- nums) {
      assertEquals(i, queue.peek)
      assertEquals(i, queue.dequeue())
    }
  }
}