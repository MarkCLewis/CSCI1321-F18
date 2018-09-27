package actorstuff

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

object ThreeActorCountdown extends App {
  class TACActor extends Actor {
    def receive = {
      case CountDown(n, next, nextnext) =>
        if(n >= 1) {
          println(self.path.name+" saying "+n)
          next ! CountDown(n-1, nextnext, self)
        } else {
          system.terminate()
        }
      case m => println("Unhandled message in TACActor "+m)
    }
    
    def badMutatingMethod() = {
      // Do bad stuff here
    }
  }
  
  val system = ActorSystem("ThreeActors")
  val a1 = system.actorOf(Props(new TACActor), "Counter1")
  val a2 = system.actorOf(Props(new TACActor), "Counter2")
  val a3 = system.actorOf(Props(new TACActor), "Counter3")
  
  a1 ! CountDown(1000, a2, a3)
  
  case class CountDown(n: Int, next: ActorRef, nextnext: ActorRef)
}