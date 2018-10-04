package mud

import scala.io.Source
import akka.actor.Actor
import akka.actor.ActorRef

class Room(
    keyword: String,
    name: String,
    desc: String,
    exitKeys: Array[String],
    items: List[Item]
    ) extends Actor {
  private var exits: Array[Option[ActorRef]] = null
  
  import Room._
  def receive = {
    case GetExit(dir) => 
      //sender ! Player.TakeExit(getExit(dir))
    case LinkExits(rooms) => 
      exits = exitKeys.map(key => rooms.get(key))
    case m => println("Unhandled message in Room: "+m)
  }
  
  def getExit(dir: Int): Option[ActorRef] = {
    exits(dir)
  }

  override def toString(): String = {
    name+"\n"+desc
  }
}

object Room {
  // Messages sent by Player
  case class GetExit(dir: Int)
  case object PrintDescription // Doesn't print here. Tells player to print.
  case class GetItem(itemName: String)
  case class DropItem(item: Item)
  
  // Messages sent by RoomManager
  case class LinkExits(rooms: Map[String, ActorRef])
}