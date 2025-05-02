package io.github.edadma.datefns

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class DateTests extends AnyFreeSpec with Matchers {

  "Date object" - {
    "now function" - {
      "should create a valid date representing current time" in {
        val currentDate = now
        currentDate.isValid shouldBe true
        // The time should be very close to current time
        val currentTime = js.Date.now()
        Math.abs(currentDate.getTime - currentTime) should be < 1000.0
      }
    }

    "extension methods" - {
      "should convert js.Date methods to Scala types" in {
        val testDate = new js.Date(2024, 4, 15, 10, 30, 45, 500).asInstanceOf[Date]

        testDate.getDate shouldBe 15
        testDate.getFullYear shouldBe 2024
        testDate.getMonth shouldBe 4 // May (0-indexed)
        testDate.getDay shouldBe 3   // Wednesday (0-indexed, with 0 being Sunday)
        testDate.getHours shouldBe 10
        testDate.getMinutes shouldBe 30
        testDate.getSeconds shouldBe 45
        testDate.getMilliseconds shouldBe 500

        // Time should match our construction
        val expectedTime = new js.Date(2024, 4, 15, 10, 30, 45, 500).getTime
        testDate.getTime shouldBe expectedTime
      }

      "should correctly identify valid and invalid dates" in {
        val validDate   = new js.Date(2024, 4, 15).asInstanceOf[Date]
        val invalidDate = new js.Date("invalid date").asInstanceOf[Date]

        validDate.isValid shouldBe true
        invalidDate.isValid shouldBe false

        // Alternative function-style syntax
        isValid(validDate) shouldBe true
        isValid(invalidDate) shouldBe false
      }

      "should format ISO string correctly" in {
        // Create a date with a fixed timestamp for consistent testing
        val utcDate = new js.Date(js.Date.UTC(2024, 4, 15, 10, 30, 45, 500))
          .asInstanceOf[Date]

        utcDate.toISOString shouldBe "2024-05-15T10:30:45.500Z"
      }
    }
  }
}
