package basics

object Mandelbrot extends App {
  val maxCount = 10000
  def mandelCount(c: Complex): Int = {
    var z = new Complex(0,0)
    var cnt = 0
    while(cnt < maxCount && z.mag < 4) {
      z = z*z+c
      cnt += 1
    }
    cnt
  }
}