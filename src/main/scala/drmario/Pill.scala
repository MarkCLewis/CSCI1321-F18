package drmario

class Pill extends Entity {
  private var pieces = List(new PillPiece(4, 0), new PillPiece(5, 0))
  
  def blocks: List[Block] = pieces
  
  def isSupported(): Boolean = ???
  
  def move(dx: Int, dy: Int): Unit = {
    for(p <- pieces) p.move(dx, dy)
  }
}