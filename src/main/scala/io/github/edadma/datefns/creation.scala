package io.github.edadma.datefns

import scala.scalajs.js

/** Creates a date from year, month, day, and time components.
  *
  * @param year
  *   The full year (e.g., 2023)
  * @param month
  *   The month index (0-11, where 0 is January and 11 is December)
  * @param day
  *   The day of the month (1-31)
  * @param hours
  *   The hour (0-23)
  * @param minutes
  *   The minutes (0-59)
  * @param seconds
  *   The seconds (0-59)
  * @param milliseconds
  *   The milliseconds (0-999)
  * @return
  *   A new Date object
  *
  * @example
  *   {{{val christmasEve = newDate(2023, 11, 24, 20, 0, 0) // December 24, 2023 at 8:00:00 PM}}}
  */
def newDate(
    year: Int,
    month: Int,
    day: Int = 1,
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0,
    milliseconds: Int = 0,
): Date = new js.Date(year, month, day, hours, minutes, seconds, milliseconds).asInstanceOf[Date]

/** Parses an ISO 8601 formatted date string (such as 2023-12-25T14:30:45.123Z) into a Date object.
  *
  * This function relies on the JavaScript Date constructor's ability to parse ISO 8601 strings.
  *
  * @param dateString
  *   ISO 8601 formatted date string to parse
  * @return
  *   A new Date object representing the parsed date
  *
  * @example
  *   {{{val date = parseISO("2023-12-25T14:30:45.123Z") // December 25, 2023 at 14:30:45.123}}}
  */
def parseISO(dateString: String): Date = new js.Date(dateString).asInstanceOf[Date]

/** Creates a Date from a Unix timestamp (seconds since January 1, 1970, 00:00:00 UTC).
  *
  * @param timestamp
  *   The number of seconds since the Unix epoch
  * @return
  *   A new Date object representing the timestamp
  *
  * @example
  *   {{{val date = fromUnixTime(1640430000) // December 25, 2021 at 09:46:40 UTC}}}
  */
def fromUnixTime(timestamp: Long): Date = new js.Date(timestamp * 1000.0).asInstanceOf[Date]
