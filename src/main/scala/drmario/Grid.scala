package drmario

class Grid {
  private var entities: List[Entity] = {
    (for (i <- 0 until 8; j <- 4 until 16; if math.random() < 0.3) yield {
      new Virus(MarioColor.Blue, i, j)
    }).toList
  }
  private var currentPill: Pill = new Pill
  private var nextPill: Pill = null
  val dropInterval = 1.0
  private var dropDelay = 0.0

  // Key Controls
  private var leftHeld = false
  private var rightHeld = false

  def leftPressed() = leftHeld = true
  def leftReleased() = leftHeld = false
  def rightPressed() = rightHeld = true
  def rightReleased() = rightHeld = false

  def blocks: List[Block] = {
    currentPill.blocks ++ entities.flatMap(_.blocks)
  }

  private def currentPieceFalls(): Unit = {
    currentPill.move(0, 1)
  }

  def update(delay: Double): Unit = {
    dropDelay += delay
    if (dropDelay >= dropInterval) {
      currentPieceFalls()
      dropDelay = 0.0
    }
    if (leftHeld) currentPill.move(-1, 0)
    if (rightHeld) currentPill.move(1, 0)
  }

  private def movePill(dx: Int, dy: Int): Unit = {
    currentPill.move(dx, dy)
  }
}