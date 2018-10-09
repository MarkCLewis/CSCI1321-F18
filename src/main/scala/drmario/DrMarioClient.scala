package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent
import java.rmi.server.UnicastRemoteObject

@remote trait RemoteClient {
  
}

object DrMarioClient extends UnicastRemoteObject with JFXApp with RemoteClient {
  val grid = ???
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