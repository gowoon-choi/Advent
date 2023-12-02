package com.hbd.advent.common.util

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object RemainDateCalculator {
    fun getRemainDayAndHour(): RemainTime {
        val current = LocalDateTime.now()
        val currentYearChristmas = LocalDateTime.of(current.year, 12, 25, 0, 0)
        val targetYear =
            if (current.isAfter(currentYearChristmas)) current.year + 1 else current.year
        val targetChristmas = LocalDateTime.of(targetYear, 12, 25, 0, 0)

        val remainDays = ChronoUnit.DAYS.between(current, targetChristmas)
        val remainHours = ChronoUnit.HOURS.between(current, targetChristmas) - (remainDays*24)
        return RemainTime(
            remainDays,
            remainHours
        )
    }

    fun getRemainDay(): Long {
        return getRemainDayAndHour().day
    }
}

data class RemainTime(
    val day: Long,
    val hour: Long
)