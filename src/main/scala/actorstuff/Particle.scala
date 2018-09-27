package actorstuff

import akka.actor.Actor

class Particle(x: Int, y: Int) extends Actor {
  import Particle._
  def receive = {
    case Float =>
      val nx = x + util.Random.nextInt(3)-1
      val ny = y + util.Random.nextInt(3)-1
      context.parent ! ParticleManager.CheckLocation(nx, ny, x, y)
    case m => 
      println("Unhandled message in Particle: "+m) 
  }
}

object Particle {
  case object Float
}