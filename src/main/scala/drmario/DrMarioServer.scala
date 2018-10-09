package drmario

import java.rmi.server.UnicastRemoteObject

@remote trait RemoteServer {
  
}

object DrMarioServer extends UnicastRemoteObject with App with RemoteServer {
  val grid = new Grid
  var lastTime = 0L
  while(true) { 
    val time = System.nanoTime()
    val delay = (time - lastTime) * 1e-9
    if (lastTime != 0L) grid.update(delay)
    lastTime = time
//    renderer.render(grid.buildPassable)
  }
}