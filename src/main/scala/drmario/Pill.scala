package drmario

class Pill extends Entity {
  private var pieces = List(new PillPiece(4, 0), new PillPiece(5, 0))

  def blocks: List[Block] = pieces

  def removeAt(pos: List[(Int, Int)]): Option[Entity] = {
    pieces = pieces.filter(pp => pos.forall { case (x,y) => pp.x != x || pp.y != y} )
    if(pieces.nonEmpty) Some(this) else None
  }

  def move(dx: Int, dy: Int): Unit = {
    for (p <- pieces) p.move(dx, dy)
  }

  def rotate(): Unit = {
    pieces = pieces.sortBy(pp => pp.x + pp.y)
    if (pieces(0).x < pieces(1).x) {
      // if horizontal, move left one to top.
      pieces(1).move(-1, -1)
    } else {
      // if vertical, move bottom to left and right down.
      pieces(0).move(0, 1)
      pieces(1).move(1, 0)
    }
  }
}