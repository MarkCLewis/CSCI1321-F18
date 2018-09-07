package drmario

import scalafx.Includes._
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  def render(grid: Grid): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, gc.canvas.width(), gc.canvas.height())
  }
}