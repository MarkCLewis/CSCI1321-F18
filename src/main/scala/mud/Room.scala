package mud

import scala.io.Source

class Room(
    keyword: String,
    name: String,
    desc: String,
    exits: Array[String],
    items: List[Item]
    ) {
  
  def getExit(dir: Int): Option[Room] = {
//    if(exits(dir) == None) None else
//      Some(Room.rooms(exits(dir).get))
    Room.rooms.get(exits(dir))
  }

  override def toString(): String = {
    name+"\n"+desc
  }
}

object Room {
  val rooms = readRooms()
  
  def readRooms(): Map[String,Room] = {
    val source = Source.fromFile("map.txt")
    val lines = source.getLines()
    val rooms = Array.fill(lines.next().trim.toInt)(readRoom(lines))
    source.close()
    rooms.toMap
  }
  
  def readRoom(lines: Iterator[String]): (String,Room) = {
    val keyword = lines.next()
    val name = lines.next()
    val desc = lines.next()
    val exits = lines.next().split(",")
    val items = List.fill(lines.next().trim.toInt){
      val Array(name, desc) = lines.next().split(",", 2)
      Item(name.trim, desc.trim)
    }
    keyword -> new Room(keyword, name, desc, exits, items)
  }
  
}