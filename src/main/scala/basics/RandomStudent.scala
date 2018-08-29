package basics

object RandomStudent extends App {
	val students = """
Akins, Dillon
Claessen, Dane
Crusius, Kevin
Flaherty, Danielle
Hildebrandt, Chase
Holt, Keaton
Jackson, Marlee
Johns, Delaney
Lobue, Nick
Reid, Chris
Rios, Rian
	""".trim.split("\n")
	println(students(util.Random.nextInt(students.length)))
}
