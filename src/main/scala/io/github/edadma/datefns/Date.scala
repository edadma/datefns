package io.github.edadma.datefns

import scala.scalajs.js

opaque type Date = js.Date

def now: Date = new js.Date

extension (d: Date)
  def getDate: Int        = d.getDate.toInt
  def getFullYear: Int    = d.getFullYear.toInt
  def getMonth: Int       = d.getMonth.toInt
  def getTime: Long       = d.getTime.toLong
  def toISOString: String = d.toISOString
