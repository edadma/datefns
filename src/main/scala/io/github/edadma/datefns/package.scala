package io.github.edadma

/** The io.github.edadma.datefns library provides utilities for working with dates in Scala.js projects.
  *
  * This library is inspired by the JavaScript date-fns library and provides similar functionality in a Scala-friendly
  * way. It wraps JavaScript's Date objects with an opaque type and provides extension methods and utility functions to
  * make working with dates more convenient.
  *
  * ==Overview==
  *
  * The library is organized into several categories of functions:
  *
  *   - '''Date creation''': Functions to create Date objects (now, parseISO, fromUnixTime, newDate)
  *   - '''Formatting''': Functions to format dates into strings (format, formatISO)
  *   - '''Comparison''': Functions to compare dates (isAfter, isBefore, isEqual, isSameDay, etc.)
  *   - '''Manipulation''': Functions to modify dates (addDays, subMonths, startOfDay, endOfMonth, etc.)
  *
  * ==Usage Examples==
  *
  * Creating a date:
  * {{{
  * import io.github.edadma.datefns.*
  *
  * // Current date and time
  * val today = now
  *
  * // Create a specific date
  * val christmas = newDate(2023, 11, 25) // December 25, 2023
  *
  * // Parse an ISO date string
  * val dateFromString = parseISO("2023-12-25T12:00:00.000Z")
  * }}}
  *
  * Formatting dates:
  * {{{
  * import io.github.edadma.datefns.*
  *
  * val date = newDate(2023, 11, 25, 14, 30)
  *
  * // Custom format
  * format(date, "MMMM d, yyyy 'at' h:mm a") // "December 25, 2023 at 2:30 PM"
  *
  * // ISO format
  * formatISO(date) // "2023-12-25T14:30:00.000Z"
  * }}}
  *
  * Comparing dates:
  * {{{
  * import io.github.edadma.datefns.*
  *
  * val date1 = newDate(2023, 0, 1)  // January 1, 2023
  * val date2 = newDate(2023, 11, 31) // December 31, 2023
  *
  * isBefore(date1, date2) // true
  * isSameYear(date1, date2) // true
  * isSameMonth(date1, date2) // false
  * }}}
  *
  * Manipulating dates:
  * {{{
  * import io.github.edadma.datefns.*
  *
  * val date = newDate(2023, 11, 25)
  *
  * val nextWeek = addDays(date, 7)
  * val previousMonth = subMonths(date, 1)
  * val startOfMonthDate = startOfMonth(date)
  * }}}
  *
  * ==Note on Month Indexing==
  *
  * Following JavaScript's Date convention, months are represented as 0-based indices: 0 = January, 1 = February, ...,
  * 11 = December.
  *
  * @see
  *   [[https://github.com/edadma/datefns GitHub Repository]]
  */
package object datefns {}
