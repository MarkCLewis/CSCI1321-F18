package drmario

trait Entity {
  def blocks: List[Block]
  def removeAt(pos: List[(Int, Int)]): Option[Entity]
}