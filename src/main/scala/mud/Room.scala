package mud

import scala.io.Source

class Room(
    name: String,
    desc: String,
    exits: Array[Option[Int]],
    items: List[Item]
    ) {
  
  def getExit(dir: Int): Option[Room] = {
//    if(exits(dir) == None) None else
//      Some(Room.rooms(exits(dir).get))
    exits(dir).map(Room.rooms)
  }

  override def toString(): String = {
    name+"\n"+desc
  }
}

object Room {
  val rooms = readRooms()
  
  def readRooms(): Array[Room] = {
    val source = Source.fromFile("map.txt")
    val lines = source.getLines()
    val rooms = Array.fill(lines.next().trim.toInt)(readRoom(lines))
    source.close()
    rooms
  }
  
  def readRoom(lines: Iterator[String]): Room = {
    val number = lines.next()
    val name = lines.next()
    val desc = lines.next()
    val exits = lines.next().split(",").
        map(_.trim.toInt).
        map(i => if(i == -1) None else Some(i))
    val items = List.fill(lines.next().trim.toInt){
      val Array(name, desc) = lines.next().split(",", 2)
      Item(name.trim, desc.trim)
    }
    new Room(name, desc, exits, items)
  }
  
}