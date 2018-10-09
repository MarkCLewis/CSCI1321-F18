package actorstuff

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatManager extends Actor {
  import ChatManager._
  def receive = {
    case CheckAllInput =>
      for(child <- context.children) child ! Chatter.CheckInput
    case NewChatter(name, sock, ps, br) =>
      for(child <- context.children) child ! Chatter.PrintMessage(name+" has arrived.")
      context.actorOf(Props(new Chatter(name, sock, ps, br)), name)
    case SendMessage(msg) =>
      for(child <- context.children; if child != sender) child ! Chatter.PrintMessage(msg)
    case m => println("Unhandled message in Chat Manager: "+m)
  }
}

object ChatManager {
  case class NewChatter(name: String, sock: Socket, ps: PrintStream, br: BufferedReader)
  case class SendMessage(msg: String)
  case object CheckAllInput
}