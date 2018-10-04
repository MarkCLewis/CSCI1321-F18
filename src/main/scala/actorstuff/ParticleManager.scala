package actorstuff

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import akka.actor.Props
import scalafx.scene.paint.Color

class ParticleManager(img: WritableImage) extends Actor {
  val writer = img.pixelWriter
  val reader = img.pixelReader.get
  
  for(i <- 0 until img.width().toInt) writer.setColor(i, img.height().toInt-1, Color.Black)
  
  for(_ <- 1 to 100) context.actorOf(Props(new Particle(img.width().toInt, img.height().toInt)))
  for(c <- context.children) c ! Particle.Float(util.Random.nextInt(500),0)
  
  import ParticleManager._
  def receive = {
    case CheckLocation(nx, ny, x, y) =>
      if(reader.getColor(nx, ny) == Color.Black) {
        writer.setColor(x, y, Color.Black)
        sender ! Particle.Float(util.Random.nextInt(500),0)
      } else {
        sender ! Particle.Float(nx, ny)
      }
    case m => println("Unhandled message in ParticleManager: "+m) 
  }
}

object ParticleManager {
  case class CheckLocation(nx: Int, ny: Int, x: Int, y: Int)
}