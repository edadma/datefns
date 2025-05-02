package io.github.edadma.datefns

def isToday(date: Date): Boolean =
  val n = now()

  date.getFullYear == n.getFullYear && date.getMonth == n.getMonth && date.getDate == n.getDate
