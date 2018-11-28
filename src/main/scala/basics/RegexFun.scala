package basics

object RegexFun extends App {
  def numberedLines(lines: List[String]): Map[Int, String] = {
    val regex = """(\d+)\.(.*)""".r
    (for (regex(num, rest) <- lines) yield {
      num.toInt -> rest
    }).toMap
  }

  def numberedLines2(lines: List[String]): Map[Int, String] = {
    val regex = """^(\d+)\.(.*)$""".r
    (for (line <- lines) yield {
      val firstMatch = regex.findFirstMatchIn(line)
      firstMatch.map(m => m.group(1).toInt -> m.group(2))
    }).flatten.toMap
  }
}