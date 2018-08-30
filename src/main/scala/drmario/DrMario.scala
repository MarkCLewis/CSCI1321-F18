package drmario

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

object DrMario extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(400, 800) { // 8 by 16 grid
      val canvas = new Canvas(400, 800)
      content = canvas
    }
  }
}