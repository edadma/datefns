package io.github.edadma.datefns

/** Formats a date according to the specified format string.
  *
  * Supported format tokens:
  *   - Year: 'yyyy' (4-digit year), 'yy' (2-digit year)
  *   - Month: 'MMMM' (full month name), 'MMM' (3-letter month name), 'MM' (2-digit month), 'M' (month number)
  *   - Day: 'dd' (2-digit day), 'd' (day number)
  *   - Day of week: 'EEEE' (full day name), 'EEE' (3-letter day name), 'E' (day of week number, 0-6)
  *   - Hours: 'HH' (2-digit 24-hour), 'H' (24-hour), 'hh' (2-digit 12-hour), 'h' (12-hour)
  *   - Minutes: 'mm' (2-digit), 'm' (minutes)
  *   - Seconds: 'ss' (2-digit), 's' (seconds)
  *   - Milliseconds: 'SSS' (3-digit milliseconds)
  *   - AM/PM: 'a'
  *
  * Text can be escaped using single quotes: 'text'
  *
  * @param date
  *   The date to format
  * @param formatStr
  *   The format string with tokens to be replaced with date values
  * @return
  *   The formatted date string
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25, 14, 30, 45)
  *
  * format(date, "yyyy-MM-dd") // "2023-12-25" format(date, "MMMM d, yyyy") // "December 25, 2023" format(date, "EEE,
  * MMM d, ''yy") // "Mon, Dec 25, '23" format(date, "h:mm a 'on' EEEE, MMMM d, yyyy") // "2:30 PM on Monday, December
  * 25, 2023" format(date, "'The year is' yyyy") // "The year is 2023" }}}
  */
def format(date: Date, formatStr: String): String =
  // Get all date components
  val year         = date.getFullYear
  val month        = date.getMonth // 0-11
  val day          = date.getDate
  val dayOfWeek    = date.getDay   // 0-6, 0 is Sunday
  val hours24      = date.getHours
  val hours12      = if (hours24 % 12 == 0) 12 else hours24 % 12
  val minutes      = date.getMinutes
  val seconds      = date.getSeconds
  val milliseconds = date.getMilliseconds
  val isPM         = hours24 >= 12

  // Define month and day names
  val monthNames = Array(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  )
  val monthNamesShort = monthNames.map(_.take(3))

  val dayNames = Array(
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
  )
  val dayNamesShort = dayNames.map(_.take(3))

  // Store quoted text to restore later - define this before using it
  var quotedTexts = Map.empty[String, String]

  // Handle quoted text (escaping)
  def handleQuotes(str: String): String =
    val singleQuotePattern = "'([^']*)'".r
    singleQuotePattern.replaceAllIn(
      str,
      m => {
        val quoted = m.group(1)
        // This is a placeholder that won't match any format patterns
        val placeholder = s"__QUOTED_${quoted.hashCode}__"
        quotedTexts += (placeholder -> quoted)
        placeholder
      },
    )

  // Handle single quotes first
  var result = handleQuotes(formatStr)

  // Format tokens
  result = result
    // Years
    .replace("yyyy", year.toString)
    .replace("yy", (year % 100).toString.padLeft(2, '0'))

    // Months
    .replace("MMMM", monthNames(month))
    .replace("MMM", monthNamesShort(month))
    .replace("MM", (month + 1).toString.padLeft(2, '0'))
    .replace("M", (month + 1).toString)

    // Days of month
    .replace("dd", day.toString.padLeft(2, '0'))
    .replace("d", day.toString)

    // Days of week
    .replace("EEEE", dayNames(dayOfWeek))
    .replace("EEE", dayNamesShort(dayOfWeek))
    .replace("E", dayOfWeek.toString)

    // Hours (24 hour)
    .replace("HH", hours24.toString.padLeft(2, '0'))
    .replace("H", hours24.toString)

    // Hours (12 hour)
    .replace("hh", hours12.toString.padLeft(2, '0'))
    .replace("h", hours12.toString)

    // Minutes
    .replace("mm", minutes.toString.padLeft(2, '0'))
    .replace("m", minutes.toString)

    // Seconds
    .replace("ss", seconds.toString.padLeft(2, '0'))
    .replace("s", seconds.toString)

    // Milliseconds
    .replace("SSS", milliseconds.toString.padLeft(3, '0'))

    // AM/PM
    .replace("a", if (isPM) "PM" else "AM")

  // Restore quoted text
  quotedTexts.foreach { case (placeholder, original) =>
    result = result.replace(placeholder, original)
  }

  result

extension (s: String)
  /** Pads a string on the left with a specified character until it reaches the desired length.
    *
    * @param length
    *   The desired length of the resulting string
    * @param padChar
    *   The character to use for padding
    * @return
    *   The padded string, or the original string if already at or exceeding the desired length
    */
  private def padLeft(length: Int, padChar: Char): String =
    if s.length >= length then s
    else padChar.toString * (length - s.length) + s

/** Formats a date according to the ISO 8601 standard.
  *
  * The format is: YYYY-MM-DDTHH:mm:ss.sssZ Where Z is the time zone offset (UTC indicated by 'Z').
  *
  * @param date
  *   The date to format
  * @return
  *   The ISO 8601 formatted date string
  *
  * @example
  *   {{{ val date = createDate(2023, 11, 25, 14, 30, 45, 123) formatISO(date) // "2023-12-25T14:30:45.123Z" (or with
  *   timezone offset) }}}
  */
def formatISO(date: Date): String = date.toISOString
