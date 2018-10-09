package actorstuff

import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.PrintStream
import java.io.InputStreamReader
import java.io.BufferedReader
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object ActorChat extends App {
  val system = ActorSystem("Chat")
  val chatManager = system.actorOf(Props[ChatManager])
  
  system.scheduler.schedule(1.seconds, 0.1.seconds, chatManager, ChatManager.CheckAllInput)

  val ss = new ServerSocket(4040)
  while (true) {
    val sock = ss.accept()
    Future {
      val ps = new PrintStream(sock.getOutputStream)
      val br = new BufferedReader(new InputStreamReader(sock.getInputStream))
      ps.println("What is your name?")
      val name = br.readLine()
      chatManager ! ChatManager.NewChatter(name, sock, ps, br)
    }
  }
}