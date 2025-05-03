package io.github.edadma.datefns

@main def run(): Unit =
  val testDate: Date = newDate(2023, 11, 25, 14, 30, 45, 678)
  val result         = format(testDate, "EEEE, MMMM d, yyyy 'at' h:mm a")

  println(result)
