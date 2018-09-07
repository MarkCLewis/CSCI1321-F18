package drmario

trait Block {
  def x: Int
  def y: Int
  def color: MarioColor.Value
}