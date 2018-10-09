package drmario

class Grid {
  private val width = 8
  private val height = 16
  private var entities: List[Entity] = {
    (for (i <- 0 until width; j <- 4 until height; if math.random() < 0.3) yield {
      new Virus(MarioColor.random(), i, j)
    }).toList
  }
  private val grid = Array.fill[Option[(MarioColor.Value, Entity)]](width + 1, height + 1)(None)
  redoGrid()
  private var currentPill: Pill = new Pill
  private var nextPill: Pill = new Pill
  private var falling = false
  val dropInterval = 1.0
  val moveInterval = 0.1
  val fallInterval = 0.3
  private var dropDelay = 0.0
  private var moveDelay = 0.0

  // Key Controls
  private var leftHeld = false
  private var rightHeld = false
  private var downHeld = false
  private var upHeld = false

  def leftPressed() = leftHeld = true
  def leftReleased() = leftHeld = false
  def rightPressed() = rightHeld = true
  def rightReleased() = rightHeld = false
  def upPressed() = upHeld = true
  def downPressed() = downHeld = true
  def downReleased() = downHeld = false

  def blocks: List[Block] = {
    currentPill.blocks ++ entities.flatMap(_.blocks)
  }

  private def currentPieceFalls(): Unit = {
    val fell = movePill(0, 1)
    if (!fell) {
      entities ::= currentPill
      redoGrid()
      doRemove()
      falling = true
    }
  }

  private def redoGrid(): Unit = {
    for (i <- grid.indices; j <- grid(i).indices) grid(i)(j) = None
    for (e <- entities; b <- e.blocks) grid(b.x)(b.y) = Some(b.color -> e)
  }

  def update(delay: Double): Unit = {
    if (falling) {
      dropDelay += delay
      if (dropDelay > fallInterval) {
        var noneFell = true
        val canFall = entities.collect { case p: Pill => p }.sortBy(-_.blocks.minBy(_.y).y)
        for (p <- canFall) {
          if (p.blocks.forall(b => b.y < height - 1 && (grid(b.x)(b.y + 1).isEmpty || grid(b.x)(b.y + 1).get._1 == p))) {
            for (b <- p.blocks) grid(b.x)(b.y) = None
            p.move(0, 1)
            for (b <- p.blocks) grid(b.x)(b.y) = Some(b.color -> p)
            noneFell = false
          }
        }
        if (noneFell) {
          val removed = doRemove()
          if (removed == 0) {
            currentPill = nextPill
            nextPill = new Pill
            falling = false
          }
        }
        dropDelay = 0.0
      }
    } else {
      dropDelay += delay
      if (dropDelay >= dropInterval) {
        currentPieceFalls()
        dropDelay = 0.0
      }
      moveDelay += delay
      if (moveDelay >= moveInterval) {
        if (leftHeld) movePill(-1, 0)
        if (rightHeld) movePill(1, 0)
        if (downHeld) currentPieceFalls()
        moveDelay = 0.0
      }
      if (upHeld) {
        rotatePill()
        upHeld = false
      }
    }
  }

  private def pillValid(): Boolean = {
    currentPill.blocks.exists(b => b.x < 0 || b.x >= width || b.y >= height ||
      (b.y >= 0 && grid(b.x)(b.y).nonEmpty))
  }

  private def movePill(dx: Int, dy: Int): Boolean = {
    currentPill.move(dx, dy)
    if (pillValid()) {
      currentPill.move(-dx, -dy)
      false
    } else true
  }

  private def rotatePill(): Boolean = {
    currentPill.rotate()
    if (pillValid()) {
      currentPill.rotate()
      currentPill.rotate()
      currentPill.rotate()
      false
    } else true
  }

  private def doRemove(): Int = {
    val toRemove = collection.mutable.Map[Entity, List[(Int, Int)]]()
    var cnt = 0
    for (i <- 0 until width) {
      var lastColor: Option[MarioColor.Value] = None
      var cnt = 0
      for (j <- 0 to height) {
        if (grid(i)(j).map(_._1) == lastColor) {
          cnt += 1
        } else {
          if (lastColor.nonEmpty && cnt >= 4) {
            for (k <- 1 to cnt) toRemove(grid(i)(j - k).get._2) = (i, j - k) :: toRemove.get(grid(i)(j - k).get._2).getOrElse(Nil)
            cnt += 1
          }
          cnt = 1
          lastColor = grid(i)(j).map(_._1)
        }
      }
    }
    for (j <- 0 until height) {
      var lastColor: Option[MarioColor.Value] = None
      var cnt = 0
      for (i <- 0 to width) {
        if (grid(i)(j).map(_._1) == lastColor) {
          cnt += 1
        } else {
          if (lastColor.nonEmpty && cnt >= 4) {
            for (k <- 1 to cnt) toRemove(grid(i - k)(j).get._2) = (i - k, j) :: toRemove.get(grid(i - k)(j).get._2).getOrElse(Nil)
            cnt += 1
          }
          cnt = 1
          lastColor = grid(i)(j).map(_._1)
        }
      }
    }
    entities = entities.flatMap { e =>
      if (toRemove.contains(e)) e.removeAt(toRemove(e)) else Some(e)
    }
    redoGrid()
    cnt
  }
}