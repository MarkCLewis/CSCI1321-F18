package drmario

import scalafx.Includes._
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Renderer(gc: GraphicsContext) {
  val blockSize = 50

  def render(grids: Seq[PassableGrid]): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, gc.canvas.width(), gc.canvas.height())

    // Draw Blocks
    for ((grid, i) <- grids.zipWithIndex; block <- grid.blocks) {
      block.color match {
        case MarioColor.Blue => gc.fill = Color.Blue
        case MarioColor.Red => gc.fill = Color.Red
        case MarioColor.Yellow => gc.fill = Color.Yellow
      }
      block match {
        case v: Virus =>
          gc.fillOval(i * 600 + v.x * blockSize, v.y * blockSize, blockSize, blockSize)
        case pp: PillPiece =>
          gc.fillRect(i * 600 + pp.x * blockSize, pp.y * blockSize, blockSize, blockSize)
      }
    }
  }
}