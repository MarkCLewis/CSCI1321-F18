package drmario

class PillPiece(private var _x: Int, private var _y: Int) extends Block {
  val _color = MarioColor.random()
  def color: MarioColor.Value = _color
  
  def x: Int = _x
  
  def y: Int = _y

  def move(dx: Int, dy: Int): Unit = {
    _x += dx
    _y += dy
  }

}