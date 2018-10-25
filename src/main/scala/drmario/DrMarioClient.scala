package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent
import java.rmi.server.UnicastRemoteObject
import scalafx.application.Platform
import java.rmi.Naming

@remote trait RemoteClient {
  def drawGrid(grids: Seq[PassableGrid]): Unit
}

object DrMarioClient extends UnicastRemoteObject with JFXApp with RemoteClient {
  val server = Naming.lookup("rmi://localhost/DrMarioServer") match {
    case s:RemoteServer => s
  }
  val grid: RemoteGrid = server.connect(this)
  val canvas = new Canvas(1000, 800)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer(gc)
  
  def drawGrid(grids: Seq[PassableGrid]): Unit = {
    Platform.runLater(renderer.render(grids))
  }
  
  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(1000, 800) { // 8 by 16 grid
      content = canvas
    }
    
    canvas.onKeyPressed = (e: KeyEvent) => {
      e.code match {
        case KeyCode.Left =>
          grid.leftPressed()
        case KeyCode.Right =>
          grid.rightPressed()
        case KeyCode.Down =>
          grid.downPressed()
        case KeyCode.Up =>
          grid.upPressed()
        case _ =>
      }
    }
    canvas.onKeyReleased = (e: KeyEvent) => {
      e.code match {
        case KeyCode.Left =>
          grid.leftReleased()
        case KeyCode.Right =>
          grid.rightReleased()
        case KeyCode.Down =>
          grid.downReleased()
        case _ =>
      }
    }
    canvas.requestFocus()
  }
}