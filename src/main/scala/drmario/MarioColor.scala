package drmario

object MarioColor extends Enumeration {
  val Red, Blue, Yellow = Value
  
  def random() = util.Random.nextInt(3) match {
    case 0 => Red
    case 1 => Blue
    case 2 => Yellow
  }
}