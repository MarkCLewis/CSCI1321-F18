package basics

object TestHitBox {
  def main(args: Array[String]): Unit = {
    {
      val hb1 = new HitBox
      val hb2 = new HitBox
      println(hb1.intersects(hb2))
      println(hb1.cx)
    }
    {
      val hb1 = new HitBox()
      val hb2 = new HitBox(3, 0, 1.0, 0.5)
      println(hb1.intersects(hb2))
    }
  }
}