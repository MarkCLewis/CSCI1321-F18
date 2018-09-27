package actorstuff

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import akka.actor.Props
import scalafx.scene.paint.Color

class ParticleManager(img: WritableImage) extends Actor {
  val writer = img.pixelWriter
  val reader = img.pixelReader.get
  
  for(_ <- 1 to 100) context.actorOf(Props(new Particle(util.Random.nextInt(500),0)))
  
  import ParticleManager._
  def receive = {
    case CheckLocation(nx, ny, x, y) =>
      if(x<0 || x>=img.width() || y<0 || y>=img.height()) {
        
      } else if(reader.getColor(nx, ny) == Color.Black) {
        
      } else {
        
      }
    case m => println("Unhandled message in ParticleManager: "+m) 
  }
}

object ParticleManager {
  case class CheckLocation(nx: Int, ny: Int, x: Int, y: Int)
}