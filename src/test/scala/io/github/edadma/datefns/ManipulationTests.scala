package io.github.edadma.datefns

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class ManipulationTests extends AnyFreeSpec with Matchers {

  // Helper to create dates with fixed values
  def newDate(
      year: Int,
      month: Int,
      day: Int,
      hours: Int = 0,
      minutes: Int = 0,
      seconds: Int = 0,
      ms: Int = 0,
  ): Date = {
    new js.Date(year, month, day, hours, minutes, seconds, ms).asInstanceOf[Date]
  }

  "Date manipulation functions" - {
    "addDays" - {
      "should add the specified number of days" in {
        val start  = newDate(2023, 11, 25)
        val result = addDays(start, 7)

        result.getFullYear shouldBe 2024
        result.getMonth shouldBe 0 // January
        result.getDate shouldBe 1  // 1st
      }

      "should handle negative days" in {
        val start  = newDate(2023, 0, 1) // Jan 1, 2023
        val result = addDays(start, -7)

        result.getFullYear shouldBe 2022
        result.getMonth shouldBe 11 // December
        result.getDate shouldBe 25  // 25th
      }
    }

    "addMonths" - {
      "should add the specified number of months" in {
        val start  = newDate(2023, 2, 15) // March 15, 2023
        val result = addMonths(start, 3)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 5 // June
        result.getDate shouldBe 15 // 15th
      }

      "should handle end-of-month edge cases" in {
        val jan31  = newDate(2023, 0, 31) // January 31, 2023
        val result = addMonths(jan31, 1)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 1 // February
        result.getDate shouldBe 28 // Feb 28 (2023 is not a leap year)
      }

      "should handle leap years correctly" in {
        val jan31LeapYear = newDate(2020, 0, 31) // January 31, 2020 (leap year)
        val result        = addMonths(jan31LeapYear, 1)

        result.getFullYear shouldBe 2020
        result.getMonth shouldBe 1 // February
        result.getDate shouldBe 29 // Feb 29 (leap year)
      }
    }

    "addYears" - {
      "should add the specified number of years" in {
        val start  = newDate(2023, 5, 15) // June 15, 2023
        val result = addYears(start, 5)

        result.getFullYear shouldBe 2028
        result.getMonth shouldBe 5 // June
        result.getDate shouldBe 15 // 15th
      }

      "should handle leap year edge case" in {
        val feb29  = newDate(2020, 1, 29) // Feb 29, 2020 (leap year)
        val result = addYears(feb29, 1)

        result.getFullYear shouldBe 2021
        result.getMonth shouldBe 2 // March
        result.getDate shouldBe 1  // Mar 1 (not a leap year)
      }
    }

    "subtraction functions" - {
      "subDays should subtract days correctly" in {
        val start  = newDate(2023, 0, 1) // Jan 1, 2023
        val result = subDays(start, 7)

        result.getFullYear shouldBe 2022
        result.getMonth shouldBe 11 // December
        result.getDate shouldBe 25  // 25th
      }

      "subMonths should subtract months correctly" in {
        val start  = newDate(2023, 2, 15) // March 15, 2023
        val result = subMonths(start, 3)

        result.getFullYear shouldBe 2022
        result.getMonth shouldBe 11 // December
        result.getDate shouldBe 15  // 15th
      }

      "subYears should subtract years correctly" in {
        val start  = newDate(2023, 5, 15) // June 15, 2023
        val result = subYears(start, 3)

        result.getFullYear shouldBe 2020
        result.getMonth shouldBe 5 // June
        result.getDate shouldBe 15 // 15th
      }
    }

    "day boundary functions" - {
      "startOfDay should set time to beginning of day" in {
        val date   = newDate(2023, 11, 25, 14, 30, 45, 500)
        val result = startOfDay(date)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 11
        result.getDate shouldBe 25
        result.getHours shouldBe 0
        result.getMinutes shouldBe 0
        result.getSeconds shouldBe 0
        result.getMilliseconds shouldBe 0
      }

      "endOfDay should set time to end of day" in {
        val date   = newDate(2023, 11, 25, 14, 30, 45)
        val result = endOfDay(date)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 11
        result.getDate shouldBe 25
        result.getHours shouldBe 23
        result.getMinutes shouldBe 59
        result.getSeconds shouldBe 59
        result.getMilliseconds shouldBe 999
      }
    }

    "month boundary functions" - {
      "startOfMonth should set date to beginning of month" in {
        val date   = newDate(2023, 11, 25, 14, 30, 45)
        val result = startOfMonth(date)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 11
        result.getDate shouldBe 1
        result.getHours shouldBe 0
        result.getMinutes shouldBe 0
        result.getSeconds shouldBe 0
        result.getMilliseconds shouldBe 0
      }

      "endOfMonth should set date to end of month" in {
        val date   = newDate(2023, 11, 15, 14, 30, 45)
        val result = endOfMonth(date)

        result.getFullYear shouldBe 2023
        result.getMonth shouldBe 11
        result.getDate shouldBe 31 // December has 31 days
        result.getHours shouldBe 23
        result.getMinutes shouldBe 59
        result.getSeconds shouldBe 59
        result.getMilliseconds shouldBe 999
      }

      "endOfMonth should handle different month lengths" in {
        // February in non-leap year
        val febDate   = newDate(2023, 1, 15)
        val febResult = endOfMonth(febDate)
        febResult.getDate shouldBe 28

        // February in leap year
        val febLeapDate   = newDate(2024, 1, 15)
        val febLeapResult = endOfMonth(febLeapDate)
        febLeapResult.getDate shouldBe 29

        // April (30 days)
        val aprDate   = newDate(2023, 3, 15)
        val aprResult = endOfMonth(aprDate)
        aprResult.getDate shouldBe 30
      }
    }
  }
}
