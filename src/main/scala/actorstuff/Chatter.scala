package actorstuff

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket

class Chatter(name: String, sock: Socket, ps: PrintStream, br: BufferedReader) extends Actor {
  import Chatter._
  
  def receive = {
    case CheckInput =>
      if(br.ready()) {
        val input = br.readLine()
        context.parent ! ChatManager.SendMessage(name+" said - "+input)
      }
    case PrintMessage(msg) =>
      ps.println(msg)
    case m => println("Unhandled message in Chatter: "+m)
  }

}

object Chatter {
  case class PrintMessage(msg: String)
  case object CheckInput
}