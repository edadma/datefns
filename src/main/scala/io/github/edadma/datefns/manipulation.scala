package io.github.edadma.datefns

import scala.scalajs.js

/** Adds the specified number of days to the given date.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of days to be added (can be negative)
  * @return
  *   A new date with the days added
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25) // December 25, 2023 val newDate = addDays(date, 7) // January 1, 2024 }}}
  */
def addDays(date: Date, amount: Int): Date =
  val result = new js.Date(date.getTime)
  result.setDate(date.getDate + amount)
  result.asInstanceOf[Date]

/** Adds the specified number of months to the given date. If the date of the given month has fewer days than the target
  * month, the date will be adjusted to the last day of the target month.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of months to be added (can be negative)
  * @return
  *   A new date with the months added
  *
  * @example
  *   {{{ val date = createDate(2023, 0, 31) // January 31, 2023 val newDate = addMonths(date, 1) // February 28, 2023
  *   (adjusted to last day of Feb)
  *
  * val marchDate = createDate(2023, 2, 15) // March 15, 2023 val juneDate = addMonths(marchDate, 3) // June 15, 2023
  * }}}
  */
def addMonths(date: Date, amount: Int): Date =
  val result = new js.Date(date.getTime)
  result.setMonth(date.getMonth + amount)
  result.asInstanceOf[Date]

/** Adds the specified number of years to the given date. Takes leap years into account; if the original date is
  * February 29 and the new year is not a leap year, the result will be February 28.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of years to be added (can be negative)
  * @return
  *   A new date with the years added
  *
  * @example
  *   {{{ val date = createDate(2020, 1, 29) // February 29, 2020 (leap year) val newDate = addYears(date, 1) //
  *   February 28, 2021 (not leap year)
  *
  * val regularDate = createDate(2023, 5, 15) // June 15, 2023 val futureDate = addYears(regularDate, 5) // June 15,
  * 2028 }}}
  */
def addYears(date: Date, amount: Int): Date =
  val result = new js.Date(date.getTime)
  result.setFullYear(date.getFullYear + amount)
  result.asInstanceOf[Date]

/** Subtracts the specified number of days from the given date. This is a convenience function that calls addDays with a
  * negative amount.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of days to be subtracted
  * @return
  *   A new date with the days subtracted
  *
  * @example
  *   {{{ val date = createDate(2023, 0, 1) // January 1, 2023 val newDate = subDays(date, 7) // December 25, 2022 }}}
  *
  * @see
  *   [[addDays]]
  */
def subDays(date: Date, amount: Int): Date = addDays(date, -amount)

/** Subtracts the specified number of months from the given date. This is a convenience function that calls addMonths
  * with a negative amount.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of months to be subtracted
  * @return
  *   A new date with the months subtracted
  *
  * @example
  *   {{{ val date = createDate(2023, 2, 15) // March 15, 2023 val newDate = subMonths(date, 3) // December 15, 2022 }}}
  *
  * @see
  *   [[addMonths]]
  */
def subMonths(date: Date, amount: Int): Date = addMonths(date, -amount)

/** Subtracts the specified number of years from the given date. This is a convenience function that calls addYears with
  * a negative amount.
  *
  * @param date
  *   The date to be changed
  * @param amount
  *   The amount of years to be subtracted
  * @return
  *   A new date with the years subtracted
  *
  * @example
  *   {{{ val date = createDate(2023, 5, 15) // June 15, 2023 val newDate = subYears(date, 3) // June 15, 2020 }}}
  *
  * @see
  *   [[addYears]]
  */
def subYears(date: Date, amount: Int): Date = addYears(date, -amount)

/** Returns a new date representing the start of a day (00:00:00.000) for the given date.
  *
  * @param date
  *   The date to be changed
  * @return
  *   A new date with time set to the start of the day
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25, 14, 30, 45) // December 25, 2023, 14:30:45 val dayStart = startOfDay(date)
  *   // December 25, 2023, 00:00:00.000 }}}
  */
def startOfDay(date: Date): Date =
  val result = new js.Date(date.getTime)
  result.setHours(0, 0, 0, 0)
  result.asInstanceOf[Date]

/** Returns a new date representing the end of a day (23:59:59.999) for the given date.
  *
  * @param date
  *   The date to be changed
  * @return
  *   A new date with time set to the end of the day
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25, 14, 30, 45) // December 25, 2023, 14:30:45 val dayEnd = endOfDay(date) //
  *   December 25, 2023, 23:59:59.999 }}}
  */
def endOfDay(date: Date): Date =
  val result = new js.Date(date.getTime)
  result.setHours(23, 59, 59, 999)
  result.asInstanceOf[Date]

/** Returns a new date representing the start of a month (1st day, 00:00:00.000) for the given date.
  *
  * @param date
  *   The date to be changed
  * @return
  *   A new date set to the start of the month
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25, 14, 30, 45) // December 25, 2023, 14:30:45 val monthStart =
  *   startOfMonth(date) // December 1, 2023, 00:00:00.000 }}}
  */
def startOfMonth(date: Date): Date =
  val result = new js.Date(date.getTime)
  result.setDate(1)
  result.setHours(0, 0, 0, 0)
  result.asInstanceOf[Date]

/** Returns a new date representing the end of a month (last day, 23:59:59.999) for the given date.
  *
  * @param date
  *   The date to be changed
  * @return
  *   A new date set to the end of the month
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 15, 14, 30, 45) // December 15, 2023, 14:30:45 val monthEnd = endOfMonth(date)
  *   // December 31, 2023, 23:59:59.999
  *
  * val febDate = createDate(2024, 1, 15) // February 15, 2024 (leap year) val febEnd = endOfMonth(febDate) // February
  * 29, 2024, 23:59:59.999 }}}
  */
def endOfMonth(date: Date): Date =
  val result = new js.Date(date.getTime)
  result.setMonth(date.getMonth + 1)
  result.setDate(0)
  result.setHours(23, 59, 59, 999)
  result.asInstanceOf[Date]
