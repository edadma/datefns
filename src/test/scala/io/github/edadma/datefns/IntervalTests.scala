package io.github.edadma.datefns

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class IntervalTests extends AnyFreeSpec with Matchers {

  // Helper to create dates with fixed values
  def newDate(year: Int, month: Int, day: Int): Date = {
    new js.Date(year, month, day).asInstanceOf[Date]
  }

  "Interval class" - {
    "should store start and end dates" in {
      val start = newDate(2023, 0, 1)   // Jan 1, 2023
      val end   = newDate(2023, 11, 31) // Dec 31, 2023

      val interval = Interval(start, end)

      interval.start shouldBe start
      interval.end shouldBe end
    }

    "should work with isWithinInterval function" in {
      val start   = newDate(2023, 0, 1)   // Jan 1, 2023
      val middle  = newDate(2023, 6, 15)  // July 15, 2023
      val end     = newDate(2023, 11, 31) // Dec 31, 2023
      val outside = newDate(2024, 0, 1)   // Jan 1, 2024

      val interval = Interval(start, end)

      isWithinInterval(start, interval) shouldBe true
      isWithinInterval(middle, interval) shouldBe true
      isWithinInterval(end, interval) shouldBe true
      isWithinInterval(outside, interval) shouldBe false
    }

    "should support intervals with same start and end" in {
      val sameDay = newDate(2023, 5, 15) // June 15, 2023

      val interval = Interval(sameDay, sameDay)

      isWithinInterval(sameDay, interval) shouldBe true
    }
  }
}
