package io.github.edadma.datefns

/** Represents a time interval between two dates.
  *
  * An interval is defined by a start date and an end date. It includes all the time from the start date up to and
  * including the end date. For most operations, it's expected that the start date is chronologically before or equal to
  * the end date.
  *
  * Intervals are useful for:
  *   - Checking if a date falls within a specific time range
  *   - Processing a range of dates between two boundaries
  *   - Computing the duration between two dates
  *   - Finding overlaps between different date ranges
  *
  * @param start
  *   The starting date of the interval (inclusive)
  * @param end
  *   The ending date of the interval (inclusive)
  *
  * @example
  *   {{{ // Create an interval for the year 2023 val year2023 = Interval( newDate(2023, 0, 1), // January 1, 2023
  *   newDate(2023, 11, 31) // December 31, 2023 )
  *
  * // Create a week interval val weekInterval = Interval( newDate(2023, 5, 12), // June 12, 2023 newDate(2023, 5,
  * 18) // June 18, 2023 )
  *
  * // Create an interval between now and a week from now val currentWeek = Interval( now, addDays(now, 7) ) }}}
  */
case class Interval(start: Date, end: Date)
