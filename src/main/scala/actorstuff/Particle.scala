package actorstuff

import akka.actor.Actor

class Particle(width: Int, height: Int) extends Actor {
  import Particle._
  def receive = {
    case Float(x, y) =>
      var nx = x + util.Random.nextInt(3)-1
      var ny = y + util.Random.nextInt(2)//-1
      while(nx<0 || nx>=width || ny<0 || ny>=height) {
    	  nx = x + util.Random.nextInt(3)-1
 			  ny = y + util.Random.nextInt(2)//-1
      }
      context.parent ! ParticleManager.CheckLocation(nx, ny, x, y)
    case m => 
      println("Unhandled message in Particle: "+m) 
  }
}

object Particle {
  case class Float(x: Int, y: Int)
}