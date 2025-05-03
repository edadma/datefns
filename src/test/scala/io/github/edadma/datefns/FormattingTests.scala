package io.github.edadma.datefns

import org.scalatest._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class FormattingTests extends AnyFreeSpec with Matchers {

  // Create a fixed date for consistent testing
  val testDate: Date = newDate(2023, 11, 25, 14, 30, 45, 678)

  "Date formatting functions" - {
    "format" - {
      "should format years correctly" in {
        format(testDate, "yyyy") shouldBe "2023"
        format(testDate, "yy") shouldBe "23"
      }

      "should format months correctly" in {
        format(testDate, "MMMM") shouldBe "December"
        format(testDate, "MMM") shouldBe "Dec"
        format(testDate, "MM") shouldBe "12"
        format(testDate, "M") shouldBe "12"
      }

      "should format days correctly" in {
        format(testDate, "dd") shouldBe "25"
        format(testDate, "d") shouldBe "25"
        format(testDate, "EEEE") shouldBe "Monday"
        format(testDate, "EEE") shouldBe "Mon"
        format(testDate, "E") shouldBe "1" // Monday is day 1
      }

      "should format time correctly" in {
        format(testDate, "HH:mm:ss") shouldBe "14:30:45"
        format(testDate, "H:m:s") shouldBe "14:30:45"
        format(testDate, "hh:mm a") shouldBe "02:30 PM"
        format(testDate, "h:mm a") shouldBe "2:30 PM"
        format(testDate, "SSS") shouldBe "678"
      }

      "should handle escaped text correctly" in {
        format(testDate, "'The date is' yyyy-MM-dd") shouldBe "The date is 2023-12-25"
        format(testDate, "'Year:' yyyy 'Month:' MM 'Day:' dd") shouldBe "Year: 2023 Month: 12 Day: 25"
        format(testDate, "yyyy-MM-dd'T'HH:mm:ss") shouldBe "2023-12-25T14:30:45"
      }

      "should support combined format patterns" in {
        format(testDate, "EEEE, MMMM d, yyyy 'at' h:mm a") shouldBe
          "Monday, December 25, 2023 at 2:30 PM"

        format(testDate, "yyyy-MM-dd HH:mm:ss.SSS") shouldBe
          "2023-12-25 14:30:45.678"
      }
    }

    "formatISO" - {
      "should produce correct ISO 8601 format" in {
        // Create a fixed UTC date to avoid timezone issues in testing
        val utcDate = new js.Date(js.Date.UTC(2023, 11, 25, 14, 30, 45, 678))
          .asInstanceOf[Date]

        formatISO(utcDate) shouldBe "2023-12-25T14:30:45.678Z"
      }
    }

    "String.padLeft" - {
      "should pad strings correctly" in {
        // We need to test this indirectly since it's a private extension method
        format(new js.Date(2023, 11, 5, 3, 5, 7).asInstanceOf[Date], "MM-dd HH:mm:ss") shouldBe
          "12-05 03:05:07"
      }
    }
  }
}
