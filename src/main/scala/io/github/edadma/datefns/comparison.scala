package io.github.edadma.datefns

/** Checks if the given date is today in the local time zone.
  *
  * @param date
  *   The date to check
  * @return
  *   True if the given date is today, false otherwise
  *
  * @example
  *   {{{ val someDate = newDate(2023, 6, 15) // July 15, 2023 isToday(someDate) // false (unless today happens to be
  *   July 15, 2023)
  *
  * isToday(now) // always true }}}
  */
def isToday(date: Date): Boolean =
  val n = now

  date.getFullYear == n.getFullYear && date.getMonth == n.getMonth && date.getDate == n.getDate

/** Checks if the first date is after the second date.
  *
  * @param date
  *   The date to check
  * @param dateToCompare
  *   The date to compare against
  * @return
  *   True if the first date is after the second date, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 11, 25) // December 25, 2023 val date2 = newDate(2023, 0, 1) // January 1,
  *   2023
  *
  * isAfter(date1, date2) // true isAfter(date2, date1) // false }}}
  */
def isAfter(date: Date, dateToCompare: Date): Boolean = date.getTime > dateToCompare.getTime

/** Checks if the first date is before the second date.
  *
  * @param date
  *   The date to check
  * @param dateToCompare
  *   The date to compare against
  * @return
  *   True if the first date is before the second date, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 0, 1) // January 1, 2023 val date2 = newDate(2023, 11, 25) // December 25,
  *   2023
  *
  * isBefore(date1, date2) // true isBefore(date2, date1) // false }}}
  */
def isBefore(date: Date, dateToCompare: Date): Boolean = date.getTime < dateToCompare.getTime

/** Checks if two dates are equal to each other. Dates are considered equal if they represent the same millisecond
  * timestamp.
  *
  * @param date1
  *   The first date to compare
  * @param date2
  *   The second date to compare
  * @return
  *   True if the dates are equal, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 11, 25, 12, 0, 0) val date2 = newDate(2023, 11, 25, 12, 0, 0) val date3 =
  *   newDate(2023, 11, 25, 12, 0, 1)
  *
  * isEqual(date1, date2) // true isEqual(date1, date3) // false }}}
  */
def isEqual(date1: Date, date2: Date): Boolean = date1.getTime == date2.getTime

/** Checks if two dates fall on the same day (ignoring time parts).
  *
  * @param date1
  *   The first date to check
  * @param date2
  *   The second date to check
  * @return
  *   True if the dates are on the same day, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 11, 25, 12, 0, 0) val date2 = newDate(2023, 11, 25, 18, 30, 0) val date3 =
  *   newDate(2023, 11, 26, 12, 0, 0)
  *
  * isSameDay(date1, date2) // true isSameDay(date1, date3) // false }}}
  */
def isSameDay(date1: Date, date2: Date): Boolean =
  date1.getDate == date2.getDate && date1.getMonth == date2.getMonth && date1.getFullYear == date2.getFullYear

/** Checks if the given date falls on a weekend (Saturday or Sunday).
  *
  * @param date
  *   The date to check
  * @return
  *   True if the date is a weekend day, false otherwise
  *
  * @example
  *   {{{ val saturday = newDate(2023, 11, 23) // December 23, 2023 (a Saturday) val monday = newDate(2023, 11,
  *   25) // December 25, 2023 (a Monday)
  *
  * isWeekend(saturday) // true isWeekend(monday) // false }}}
  */
def isWeekend(date: Date): Boolean =
  val day = date.getDay
  day == 0 || day == 6

/** Checks if the given date is in the future compared to current time.
  *
  * @param date
  *   The date to check
  * @return
  *   True if the date is in the future, false otherwise
  *
  * @example
  *   {{{ val futureDate = addYears(now, 1) // One year from now
  *
  * isFuture(futureDate) // true isFuture(now) // false }}}
  */
def isFuture(date: Date): Boolean = date.getTime > now.getTime

/** Checks if the given date is in the past compared to current time.
  *
  * @param date
  *   The date to check
  * @return
  *   True if the date is in the past, false otherwise
  *
  * @example
  *   {{{ val pastDate = subYears(now, 1) // One year ago
  *
  * isPast(pastDate) // true isPast(now) // false }}}
  */
def isPast(date: Date): Boolean = date.getTime < now.getTime

/** Checks if two dates fall within the same month and year.
  *
  * @param dateLeft
  *   The first date to check
  * @param dateRight
  *   The second date to check
  * @return
  *   True if the dates are in the same month and year, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 11, 1) // December 1, 2023 val date2 = newDate(2023, 11, 25) // December 25,
  *   2023 val date3 = newDate(2023, 10, 1) // November 1, 2023
  *
  * isSameMonth(date1, date2) // true isSameMonth(date1, date3) // false }}}
  */
def isSameMonth(dateLeft: Date, dateRight: Date): Boolean =
  dateLeft.getFullYear == dateRight.getFullYear && dateLeft.getMonth == dateRight.getMonth

/** Checks if two dates fall within the same year.
  *
  * @param dateLeft
  *   The first date to check
  * @param dateRight
  *   The second date to check
  * @return
  *   True if the dates are in the same year, false otherwise
  *
  * @example
  *   {{{ val date1 = newDate(2023, 0, 1) // January 1, 2023 val date2 = newDate(2023, 11, 31) // December 31,
  *   2023 val date3 = newDate(2024, 0, 1) // January 1, 2024
  *
  * isSameYear(date1, date2) // true isSameYear(date1, date3) // false }}}
  */
def isSameYear(dateLeft: Date, dateRight: Date): Boolean =
  dateLeft.getFullYear == dateRight.getFullYear

/** Checks if the date is within the given time interval. The interval boundaries are inclusive (>=start, <=end).
  *
  * @param date
  *   The date to check
  * @param interval
  *   The interval to check against, with start and end dates
  * @return
  *   True if the date is within the interval, false otherwise
  *
  * @example
  *   {{{ val start = newDate(2023, 0, 1) // January 1, 2023 val end = newDate(2023, 11, 31) // December 31, 2023
  *   val interval = Interval(start, end)
  *
  * val dateToCheck = createDate(2023, 6, 15) // July 15, 2023 val dateOutside = createDate(2024, 0, 1) // January 1,
  * 2024
  *
  * isWithinInterval(dateToCheck, interval) // true isWithinInterval(dateOutside, interval) // false
  * isWithinInterval(start, interval) // true (inclusive) isWithinInterval(end, interval) // true (inclusive) }}}
  */
def isWithinInterval(date: Date, interval: Interval): Boolean =
  val time = date.getTime
  time >= interval.start.getTime && time <= interval.end.getTime
