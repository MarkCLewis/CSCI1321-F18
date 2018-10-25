package adt

import org.junit.Test
import org.junit.Assert._
import org.junit.Before

class DLLBufferTest {
  private var lst: DLLBuffer[Int] = null

  @Before
  def init(): Unit = {
    lst = new DLLBuffer[Int]()
  }
  
  @Test
  def emptyOnCreate: Unit = {
		  assertTrue(lst.length == 0)
		  assertTrue(lst.isEmpty)
		  assertFalse(lst.nonEmpty)
  }

  @Test
  def append1AndCheck: Unit = {
    lst += 5
    assertEquals(5, lst(0))
    assertFalse(lst.isEmpty)
    assertEquals(1, lst.length)
  }

  @Test
  def append2AndCheck: Unit = {
    lst += 5 += 8
    assertEquals(5, lst(0))
    assertEquals(8, lst(1))
    assertFalse(lst.isEmpty)
    assertEquals(2, lst.length)
  }

  @Test
  def prepend1AndCheck: Unit = {
    5 +=: lst
    assertEquals(5, lst(0))
    assertFalse(lst.isEmpty)
    assertEquals(1, lst.length)
  }

  @Test
  def prepend2AndCheck: Unit = {
    8 +=: 5 +=: lst
    assertEquals(5, lst(1))
    assertEquals(8, lst(0))
    assertFalse(lst.isEmpty)
    assertEquals(2, lst.length)
  }
}