package drmario

trait Block extends Serializable {
  def x: Int
  def y: Int
  def color: MarioColor.Value
}