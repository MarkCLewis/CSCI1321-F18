package drmario

import scalafx.Includes._
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val blockSize = 50

  def render(grid: Grid): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, gc.canvas.width(), gc.canvas.height())

    // Draw Blocks
    for (block <- grid.blocks) {
      block.color match {
        case MarioColor.Blue => gc.fill = Color.Blue
        case MarioColor.Red => gc.fill = Color.Red
        case MarioColor.Yellow => gc.fill = Color.Yellow
      }
      block match {
        case v: Virus =>
          gc.fillOval(v.x * blockSize, v.y * blockSize, blockSize, blockSize)
        case pp: PillPiece =>
          gc.fillRect(pp.x * blockSize, pp.y * blockSize, blockSize, blockSize)
      }
    }
  }
}