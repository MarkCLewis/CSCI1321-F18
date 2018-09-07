package drmario

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas

object DrMario extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Dr. Mario"
    scene = new Scene(400, 800) { // 8 by 16 grid
      val canvas = new Canvas(400, 800)
      val gc = canvas.graphicsContext2D
      content = canvas
      val renderer = new Renderer(gc)
      renderer.render(null)
    }
  }
}