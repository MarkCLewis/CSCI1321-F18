package drmario

class Grid {
  private var entities: List[Entity] = {
    (for(i <- 0 until 8; j <- 4 until 16; if math.random()<0.3) yield {
      new Virus(MarioColor.Blue, i, j)
    }).toList
  }
  private var currentPill: Pill = new Pill
  private var nextPill: Pill = null
  
  def blocks: List[Block] = {
    currentPill.blocks ++ entities.flatMap(_.blocks)
  }
  
  def currentPieceFalls(): Unit = {
    currentPill.move(0, 1)
  }
}