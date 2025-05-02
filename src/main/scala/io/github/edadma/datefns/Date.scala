package io.github.edadma.datefns

import scala.scalajs.js

/** A wrapper for JavaScript's Date object in a Scala.js environment.
  *
  * This opaque type encapsulates a JavaScript Date instance with type-safe Scala methods. The wrapper provides
  * Scala-friendly access to the underlying JavaScript Date functionality while maintaining proper type signatures.
  *
  * @see
  *   [[https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date JavaScript Date documentation]]
  *
  * @example
  *   {{{
  *   // Create a new date representing the current moment
  *   val currentDate = now
  *
  *   // Access date components
  *   val currentYear = currentDate.getFullYear
  *   val currentMonth = currentDate.getMonth  // 0-11, January is 0
  *   val currentDay = currentDate.getDate     // Day of month (1-31)
  *   }}}
  */
opaque type Date = js.Date

/** Creates a new Date object representing the current moment in time.
  *
  * @return
  *   A Date object representing the current date and time
  *
  * @example
  *   {{{
  *   val currentDate = now
  *   println(s"Current time: ${currentDate.toISOString}")
  *   }}}
  */
def now: Date = new js.Date

extension (d: Date)
  /** Returns the day of the month (1-31) for this date according to local time.
    *
    * @return
    *   The day of the month (1-31)
    *
    * @example
    *   {{{
    *   val date = now
    *   val dayOfMonth = date.getDate  // For example: 15
    *   }}}
    */
  def getDate: Int = d.getDate.toInt

  /** Returns the year (4 digits) for this date according to local time.
    *
    * @return
    *   The 4-digit year
    *
    * @example
    *   {{{
    *   val date = now
    *   val year = date.getFullYear  // For example: 2024
    *   }}}
    */
  def getFullYear: Int = d.getFullYear.toInt

  /** Returns the month (0-11) for this date according to local time.
    *
    * @note
    *   Month numbering starts at 0 for January through 11 for December.
    *
    * @return
    *   The month (0-11)
    *
    * @example
    *   {{{
    *   val date = now
    *   val month = date.getMonth  // 0 for January, 11 for December
    *   }}}
    */
  def getMonth: Int = d.getMonth.toInt

  /** Returns the number of milliseconds since January 1, 1970, 00:00:00 UTC.
    *
    * @return
    *   The timestamp in milliseconds
    *
    * @example
    *   {{{
    *   val date = now
    *   val timestamp = date.getTime  // For example: 1715433729034
    *   }}}
    */
  def getTime: Long = d.getTime.toLong

  /** Returns this date as an ISO 8601 formatted string.
    *
    * The format is: YYYY-MM-DDTHH:mm:ss.sssZ
    *
    * @return
    *   The date formatted according to ISO 8601
    *
    * @example
    *   {{{
    *   val date = now
    *   val isoString = date.toISOString  // For example: "2024-05-11T14:35:29.034Z"
    *   }}}
    */
  def toISOString: String = d.toISOString

  /** Returns the day of the week (0-6) for this date according to local time.
    *
    * @note
    *   Day numbering starts at 0 for Sunday through 6 for Saturday.
    *
    * @return
    *   The day of the week (0-6)
    *
    * @example
    *   {{{
    *   val date = now
    *   val dayOfWeek = date.getDay  // 0 for Sunday, 6 for Saturday
    *   }}}
    */
  def getDay: Int = d.getDay.toInt

  /** Returns the hour (0-23) for this date according to local time.
    *
    * @return
    *   The hour (0-23)
    *
    * @example
    *   {{{
    *   val date = now
    *   val hour = date.getHours  // For example: 14
    *   }}}
    */
  def getHours: Int = d.getHours.toInt

  /** Returns the minutes (0-59) for this date according to local time.
    *
    * @return
    *   The minutes (0-59)
    *
    * @example
    *   {{{
    *   val date = now
    *   val minutes = date.getMinutes  // For example: 35
    *   }}}
    */
  def getMinutes: Int = d.getMinutes.toInt

  /** Returns the seconds (0-59) for this date according to local time.
    *
    * @return
    *   The seconds (0-59)
    *
    * @example
    *   {{{
    *   val date = now
    *   val seconds = date.getSeconds  // For example: 29
    *   }}}
    */
  def getSeconds: Int = d.getSeconds.toInt

  /** Returns the milliseconds (0-999) for this date according to local time.
    *
    * @return
    *   The milliseconds (0-999)
    *
    * @example
    *   {{{
    *   val date = now
    *   val milliseconds = date.getMilliseconds  // For example: 34
    *   }}}
    */
  def getMilliseconds: Int = d.getMilliseconds.toInt

  /** Determines if this date is valid.
    *
    * @return
    *   `true` if the date is valid, `false` otherwise
    *
    * @example
    *   {{{
    *   val validDate = now
    *   val invalidDate = new js.Date("invalid date").asInstanceOf[Date]
    *
    *   validDate.isValid   // true
    *   invalidDate.isValid // false
    *   }}}
    */
  def isValid: Boolean = !d.getTime.isNaN
