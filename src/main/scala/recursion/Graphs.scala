package recursion

object Graphs extends App {
  val graph = Array(
    Array(0, 0, 1, 1, 0),
    Array(1, 0, 1, 0, 0),
    Array(0, 1, 0, 0, 0),
    Array(0, 0, 1, 0, 0),
    Array(1, 0, 0, 1, 0))

  def reachable(node1: Int, node2: Int, connect: Array[Array[Int]],
    visited: Set[Int] = Set.empty[Int]): Boolean = {
    if (node1 == node2) true
    else if (visited(node1)) false else {
      val newVisited = visited + node1
      connect(node1).indices.exists(i => connect(node1)(i) != 0 &&
        reachable(i, node2, connect, newVisited))
    }
  }

  def shortestPath(node1: Int, node2: Int, connect: Array[Array[Int]],
    visited: Set[Int] = Set.empty[Int]): Int = {
    if (node1 == node2) 0
    else if (visited(node1)) 1000000000 else {
      val newVisited = visited + node1
      (for (i <- connect(node1).indices; if connect(node1)(i) != 0) yield {
        shortestPath(i, node2, connect, newVisited)
      }).min + 1

    }
  }
  
  def hasCycle(node: Int, connect: Array[Array[Int]], visited: Set[Int]): Boolean = {
    if(visited(node)) true else {
      val newVisited = visited + node
      connect(node).indices.exists(i => connect(node)(i) != 0 &&
        hasCycle(i, connect, newVisited))
    }
  }
  
  println(shortestPath(2, 3, graph))
  println(shortestPath(4, 2, graph))

  println(reachable(2, 3, graph))
  println(reachable(2, 4, graph))
}