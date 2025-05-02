package io.github.edadma.datefns

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class ComparisonTests extends AnyFreeSpec with Matchers {

  // Helper to create dates with fixed values
  def createTestDate(year: Int, month: Int, day: Int, hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Date = {
    new js.Date(year, month, day, hours, minutes, seconds).asInstanceOf[Date]
  }

  "Date comparison functions" - {
    "isAfter" - {
      "should correctly identify if a date is after another" in {
        val earlier = createTestDate(2023, 5, 15)
        val later   = createTestDate(2023, 6, 15)

        isAfter(later, earlier) shouldBe true
        isAfter(earlier, later) shouldBe false
        isAfter(earlier, earlier) shouldBe false
      }
    }

    "isBefore" - {
      "should correctly identify if a date is before another" in {
        val earlier = createTestDate(2023, 5, 15)
        val later   = createTestDate(2023, 6, 15)

        isBefore(earlier, later) shouldBe true
        isBefore(later, earlier) shouldBe false
        isBefore(earlier, earlier) shouldBe false
      }
    }

    "isEqual" - {
      "should correctly identify if two dates are equal" in {
        val date1 = createTestDate(2023, 5, 15, 10, 30, 0)
        val date2 = createTestDate(2023, 5, 15, 10, 30, 0)
        val date3 = createTestDate(2023, 5, 15, 10, 30, 1)

        isEqual(date1, date2) shouldBe true
        isEqual(date1, date3) shouldBe false
      }
    }

    "isSameDay" - {
      "should identify dates on the same day" in {
        val morning = createTestDate(2023, 5, 15, 9, 0, 0)
        val evening = createTestDate(2023, 5, 15, 21, 0, 0)
        val nextDay = createTestDate(2023, 5, 16, 9, 0, 0)

        isSameDay(morning, evening) shouldBe true
        isSameDay(morning, nextDay) shouldBe false
      }
    }

    "isSameMonth" - {
      "should identify dates in the same month" in {
        val day1      = createTestDate(2023, 5, 1)
        val day30     = createTestDate(2023, 5, 30)
        val nextMonth = createTestDate(2023, 6, 1)

        isSameMonth(day1, day30) shouldBe true
        isSameMonth(day1, nextMonth) shouldBe false
      }
    }

    "isSameYear" - {
      "should identify dates in the same year" in {
        val january  = createTestDate(2023, 0, 1)
        val december = createTestDate(2023, 11, 31)
        val nextYear = createTestDate(2024, 0, 1)

        isSameYear(january, december) shouldBe true
        isSameYear(january, nextYear) shouldBe false
      }
    }

    "isWeekend" - {
      "should identify weekend days correctly" in {
        // May 13, 2023 was a Saturday
        val saturday = createTestDate(2023, 4, 13)
        // May 14, 2023 was a Sunday
        val sunday = createTestDate(2023, 4, 14)
        // May 15, 2023 was a Monday
        val monday = createTestDate(2023, 4, 15)

        isWeekend(saturday) shouldBe true
        isWeekend(sunday) shouldBe true
        isWeekend(monday) shouldBe false
      }
    }

    "isWithinInterval" - {
      "should check if a date falls within an interval" in {
        val start   = createTestDate(2023, 0, 1)
        val middle  = createTestDate(2023, 6, 15)
        val end     = createTestDate(2023, 11, 31)
        val outside = createTestDate(2024, 0, 1)

        val interval = Interval(start, end)

        isWithinInterval(start, interval) shouldBe true // Inclusive
        isWithinInterval(middle, interval) shouldBe true
        isWithinInterval(end, interval) shouldBe true // Inclusive
        isWithinInterval(outside, interval) shouldBe false
      }
    }

    // Since isToday, isFuture and isPast depend on the current time,
    // they need special handling in tests
    "time-dependent functions" - {
      "isFuture and isPast should be consistent" in {
        val now = new js.Date()

        // Create dates guaranteed to be in future/past
        val future = new js.Date(now.getTime() + 10000).asInstanceOf[Date] // 10 seconds in future
        val past   = new js.Date(now.getTime() - 10000).asInstanceOf[Date] // 10 seconds in past

        isFuture(future) shouldBe true
        isFuture(past) shouldBe false

        isPast(past) shouldBe true
        isPast(future) shouldBe false
      }
    }
  }
}
