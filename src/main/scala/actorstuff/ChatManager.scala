package actorstuff

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatManager extends Actor {
  import ChatManager._
  def receive = {
    case NewChatter(name, sock, ps, br) =>
      for(child <- context.children) child ! Chatter.PrintMessage(name+" has arrived.")
      context.actorOf(Props(new Chatter(name, sock, ps, br)), name)
    case m => println("Unhandled message in Chat Manager: "+m)
  }
}

object ChatManager {
  case class NewChatter(name: String, sock: Socket, ps: PrintStream, br: BufferedReader)
}