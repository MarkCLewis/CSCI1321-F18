package basics

object PolynomialRegex extends App {
  val term = """[+-]?((\d*x(\^\d+)?)|(\d+))""".r
  val poly = "3+4x-6x^3-x^4"
  val matches = term.findAllMatchIn(poly)
  for(m <- matches) println(m)
}