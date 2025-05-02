package io.github.edadma.datefns

def isToday(date: Date): Boolean =
  val n = now

  date.getFullYear == n.getFullYear && date.getMonth == n.getMonth && date.getDate == n.getDate

def isAfter(date: Date, dateToCompare: Date): Boolean = date.getTime > dateToCompare.getTime

def isBefore(date: Date, dateToCompare: Date): Boolean = date.getTime < dateToCompare.getTime

def isEqual(date1: Date, date2: Date): Boolean = date1.getTime == date2.getTime

def isSameDay(date1: Date, date2: Date): Boolean =
  date1.getDate == date2.getDate && date1.getMonth == date2.getMonth && date1.getFullYear == date2.getFullYear
