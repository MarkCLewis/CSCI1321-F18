package drmario

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer

object DrMario extends JFXApp {
  val grid = new Grid
  val canvas = new Canvas(400, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(400, 800) { // 8 by 16 grid
      content = canvas
    }

    var dropInterval = 1.0
    var lastDrop = -1L
    val timer = AnimationTimer { time =>
      if (lastDrop < 0) lastDrop = time
      val dropDelay = (time - lastDrop) * 1e-9
      if (dropDelay >= dropInterval) {
        grid.currentPieceFalls()
        lastDrop = time
      }
      renderer.render(grid)
    }
    timer.start()
  }
}