package io.github.edadma.datefns

@main def run(): Unit =
  val feb29  = newDate(2020, 1, 29) // Feb 29, 2020 (leap year)
  val result = addYears(feb29, 1)

  println(result)
