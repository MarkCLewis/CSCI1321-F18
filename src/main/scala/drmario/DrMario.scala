package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

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
    
    canvas.onKeyPressed = (e: KeyEvent) => {
      e.code match {
        case KeyCode.Left =>
          grid.leftPressed()
        case KeyCode.Right =>
          grid.rightPressed()
        case _ =>
      }
    }
    canvas.onKeyReleased = (e: KeyEvent) => {
      e.code match {
        case KeyCode.Left =>
          grid.leftReleased()
        case KeyCode.Right =>
          grid.rightReleased()
        case _ =>
      }
    }
    canvas.requestFocus()

    var lastTime = 0L
    val timer = AnimationTimer { time =>
      val delay = (time - lastTime) * 1e-9
      if(lastTime != 0L) grid.update(delay)
      lastTime = time
      renderer.render(grid)
    }
    timer.start()
  }
}