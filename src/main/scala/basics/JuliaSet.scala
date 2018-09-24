package basics

import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import javafx.scene.image.ImageView
import scalafx.scene.paint.Color

class JuliaSet(c: Complex) {
  private var xmin = -1.5
  private var xmax = 1.5
  private var ymin = -1.5
  private var ymax = 1.5
  
  val maxCount = 10000
  def juliaCount(z0: Complex): Int = {
    var z = z0
    var cnt = 0
    while (cnt < maxCount && z.mag < 4) {
      z = z * z + c
      cnt += 1
    }
    cnt
  }

  def colorFromCount(cnt: Int): Color = {
    if (cnt == maxCount) Color.Black
    else Color(1.0, 0.0, 0.0, math.log(cnt + 1) / math.log(maxCount))
  }
  
  def pixelToX(i: Double, img: WritableImage): Double = xmin + i * (xmax - xmin) / img.width()
  def pixelToY(j: Double, img: WritableImage): Double = ymin + j * (ymax - ymin) / img.height()
  
  def drawJulia(img: WritableImage): Unit = {
    val writer = img.pixelWriter
    val start = System.nanoTime()
    for (j <- 0 until img.height().toInt) {
      val y = pixelToY(j, img)
      for (i <- 0 until img.width().toInt) {
        val x = pixelToX(i, img)
        val cnt = juliaCount(new Complex(x, y))
        val color = colorFromCount(cnt)
        writer.setColor(i, j, color)
      }
    }
    println("Took " + ((System.nanoTime() - start) * 1e-9) + " seconds.")
  }

  val stage = new scalafx.stage.Stage {
    title = "Julia for "+c.x+", "+c.y
    scene = new Scene(1000, 1000) {
      val img = new WritableImage(1000, 1000)
      val imgView = new ImageView(img)
      content = imgView
      drawJulia(img)
    }
  }
  stage.show()
}