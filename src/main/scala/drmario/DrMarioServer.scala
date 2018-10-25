package drmario

import java.rmi.server.UnicastRemoteObject
import java.rmi.registry.LocateRegistry
import java.rmi.Naming

@remote trait RemoteServer {
  def connect(client: RemoteClient): RemoteGrid
}

case class Player(grid: Grid, client: RemoteClient)
case class Game(players: Seq[Player])

object DrMarioServer extends UnicastRemoteObject with App with RemoteServer {
  LocateRegistry.createRegistry(1099)
  Naming.rebind("DrMarioServer", this)
  private var player1: Player = null
  def connect(client: RemoteClient): RemoteGrid = {
    val grid = new Grid
    if(player1 == null) player1 = Player(grid, client)
    else {
      games ::= Game(List(player1, Player(grid, client)))
      player1 = null
    }
    grid
  }
  var games = List[Game]()
  var lastTime = 0L
  val drawInterval = 0.01
  var drawDelay = 0.0
  if (false) connect(null)
  while (true) {
    val time = System.nanoTime()
    val delay = (time - lastTime) * 1e-9
    drawDelay += delay
    for (game <- games; player <- game.players) {
      if (lastTime != 0L) player.grid.update(delay)
      lastTime = time
    }
    if (drawDelay >= drawInterval) {
      for (game <- games; player <- game.players) {
        player.client.drawGrid(game.players.map(_.grid.buildPassable()))
      }
      drawDelay = 0.0
    }
  }
}