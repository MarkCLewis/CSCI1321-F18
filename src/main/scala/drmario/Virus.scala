package drmario

class Virus(
    val color: MarioColor.Value,
    val x: Int,
    val y: Int
    ) extends Block with Entity{
  
  def blocks: List[Block] = List(this)
  
  def isSupported(): Boolean = true
}