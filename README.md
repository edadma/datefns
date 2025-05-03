# datefns

![Maven Central](https://img.shields.io/maven-central/v/io.github.edadma/datefns_sjs1_3)
![GitHub](https://img.shields.io/github/license/edadma/datefns)
![Scala Version](https://img.shields.io/badge/scala-3.6.4-blue.svg)
![ScalaJS Version](https://img.shields.io/badge/scalajs-1.18.2-blue.svg)

A Scala.js library for working with dates, inspired by the JavaScript [date-fns](https://date-fns.org/) library.

## Overview

`datefns` provides a comprehensive set of utilities for manipulating, comparing, and formatting dates in Scala.js projects. It wraps JavaScript's Date objects with a type-safe Scala API, making it easier to work with dates in a functional and type-safe manner.

## Installation

Add the following dependency to your `build.sbt`:

```scala
libraryDependencies += "io.github.edadma" %%% "datefns" % "0.0.1"
```

## Features

- **Date creation**: Functions to create Date objects (`now`, `parseISO`, `newDate`)
- **Formatting**: Functions to format dates into strings
- **Comparison**: Functions to compare dates (`isAfter`, `isBefore`, `isEqual`, etc.)
- **Manipulation**: Functions to modify dates (`addDays`, `subMonths`, etc.)

## Quick Examples

```scala
import io.github.edadma.datefns.*

// Create dates
val today = now
val christmas = newDate(2023, 11, 25) // December 25, 2023 (months are 0-indexed)

// Compare dates
val isBefore = isBefore(today, christmas)
val isSameYear = isSameYear(today, christmas)

// Modify dates
val nextWeek = addDays(today, 7)
val lastMonth = subMonths(today, 1)

// Check properties
val isWeekend = isWeekend(today)
val isFutureDate = isFuture(christmas)
```

## Important Note on Month Indexing

Following JavaScript's Date convention, months are represented as 0-based indices:
- 0 = January
- 1 = February
- ...
- 11 = December

## Documentation

For complete API documentation, visit: [https://edadma.github.io/datefns/](https://edadma.github.io/datefns/)

## License

This project is licensed under the ISC License - see the project's license information for details.