package io.github.edadma.datefns

import java.util.regex.Pattern

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
  */
def format(date: Date, formatStr: String): String = {
  // Define Token classes at the beginning
  sealed trait Token
  case class FormatToken(token: String) extends Token
  case class LiteralText(text: String)  extends Token

  // Helper function to extract quoted text and replace with placeholders
  def extractQuoted(input: String): (String, Map[String, String]) = {
    val quotePattern = "'([^']*)'".r
    val quotedTexts  = collection.mutable.Map.empty[String, String]

    val resultString = quotePattern.replaceAllIn(
      input,
      m => {
        val quoted      = m.group(1)
        val placeholder = s"__QUOTED_${quotedTexts.size}__"
        quotedTexts(placeholder) = quoted
        placeholder
      },
    )

    (resultString, quotedTexts.toMap)
  }

  // Helper function to tokenize the format string
  def tokenize(input: String): List[Token] = {
    // Define all format tokens, ordered from longest to shortest to avoid partial matches
    val formatTokens = Seq(
      "yyyy",
      "MMMM",
      "EEEE",
      "MMM",
      "EEE",
      "MM",
      "dd",
      "HH",
      "hh",
      "mm",
      "ss",
      "SSS",
      "yy",
      "M",
      "d",
      "E",
      "H",
      "h",
      "m",
      "s",
      "a",
    ).distinct

    // Create a regex pattern that matches any format token or quoted placeholder
    val tokenPattern = (formatTokens.map(Pattern.quote).mkString("|") + "|__QUOTED_\\d+__").r

    // Split by tokens and create a list of Token objects
    val result  = collection.mutable.ListBuffer.empty[Token]
    var lastEnd = 0

    for (m <- tokenPattern.findAllMatchIn(input)) {
      val start = m.start

      // Add literal text between tokens
      if (start > lastEnd) {
        result += LiteralText(input.substring(lastEnd, start))
      }

      // Add the token
      result += FormatToken(m.group(0))

      lastEnd = m.end
    }

    // Add any remaining literal text
    if (lastEnd < input.length) {
      result += LiteralText(input.substring(lastEnd))
    }

    result.toList
  }

  // Now actually execute the formatting process
  val (withPlaceholders, quotedMap) = extractQuoted(formatStr)
  val tokens                        = tokenize(withPlaceholders)

  // Helper function to format a specific token
  def formatToken(token: String): String = {
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

    token match {
      // Year
      case "yyyy" => year.toString
      case "yy"   => (year % 100).toString.padLeft(2, '0')

      // Month
      case "MMMM" => monthNames(month)
      case "MMM"  => monthNamesShort(month)
      case "MM"   => (month + 1).toString.padLeft(2, '0')
      case "M"    => (month + 1).toString

      // Day
      case "dd" => day.toString.padLeft(2, '0')
      case "d"  => day.toString

      // Day of week
      case "EEEE" => dayNames(dayOfWeek)
      case "EEE"  => dayNamesShort(dayOfWeek)
      case "E"    => dayOfWeek.toString

      // Hours
      case "HH" => hours24.toString.padLeft(2, '0')
      case "H"  => hours24.toString
      case "hh" => hours12.toString.padLeft(2, '0')
      case "h"  => hours12.toString

      // Minutes, seconds, milliseconds
      case "mm"  => minutes.toString.padLeft(2, '0')
      case "m"   => minutes.toString
      case "ss"  => seconds.toString.padLeft(2, '0')
      case "s"   => seconds.toString
      case "SSS" => milliseconds.toString.padLeft(3, '0')

      // AM/PM
      case "a" => if (isPM) "PM" else "AM"

      // Quoted text placeholder (handled differently now)
      case quoted if quoted.startsWith("__QUOTED_") => quotedMap.getOrElse(quoted, quoted)

      // Fallback for unknown tokens
      case _ => token
    }
  }

  tokens.map {
    case FormatToken(token) => formatToken(token)
    case LiteralText(text)  => text
  }.mkString
}

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
