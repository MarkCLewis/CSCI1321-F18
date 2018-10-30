package adt

import org.junit.Test
import org.junit.Assert._

class TestPriorityQueues {
  @Test def ULLTest: Unit = {
    val nums = Array.fill(100)(math.random)
    val pq = new UnsortedLLPriorityQueue[Double](_<_)
    for(n <- nums) pq.enqueue(n)
    for(n <- nums.sorted) {
      assertEquals(n, pq.dequeue(),0.001)
    }
  }
  @Test def SATest: Unit = {
    val nums = Array.fill(100)(math.random)
    val pq = new SAPriorityQueue[Double](_<_)
    for(n <- nums) pq.enqueue(n)
    for(n <- nums.sorted) {
      assertEquals(n, pq.dequeue(),0.001)
    }
  }
  @Test def UATest: Unit = {
    val nums = Array.fill(100)(math.random)
    val pq = new UnsortedArrayPQ[Double](_<_)
    for(n <- nums) pq.enqueue(n)
    for(n <- nums.sorted) {
      assertEquals(n, pq.dequeue(),0.001)
    }
  }
}