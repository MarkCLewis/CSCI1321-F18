package drmario

trait Entity {
  def isSupported(): Boolean
  def blocks: List[Block]
}