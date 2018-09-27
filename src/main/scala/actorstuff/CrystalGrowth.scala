package actorstuff

import akka.actor.ActorSystem
import scalafx.application.JFXApp
import akka.actor.Props
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView

object CrystalGrowth extends JFXApp {
  val system = ActorSystem("Crystals")
  val img = new WritableImage(1000,1000)
  val a1 = system.actorOf(Props(new ParticleManager(img)), "ParticleManager")
  
  stage = new PrimaryStage {
    title = "Crystals"
    scene = new Scene(1000,1000) {
      content = new ImageView(img)
    }
  }
}