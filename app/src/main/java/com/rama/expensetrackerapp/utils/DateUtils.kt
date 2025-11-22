package com.rama.expensetrackerapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
fun getMonthStartTimestamp(year: Int, month: Int): Long {
    return LocalDate.of(year, month, 1)
        .atStartOfDay()
        .toEpochSecond(ZoneOffset.UTC) * 1000
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonthEndTimestamp(year: Int, month: Int): Long {
    val lastDay = YearMonth.of(year, month).lengthOfMonth()

    return LocalDate.of(year, month, lastDay)
        .atTime(23, 59, 59)
        .toEpochSecond(ZoneOffset.UTC) * 1000
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentMonthRange(): Pair<Long, Long> {
    val now = LocalDate.now()

    val start = now.withDayOfMonth(1)
        .atStartOfDay()
        .toEpochSecond(ZoneOffset.UTC) * 1000

    val end = now.withDayOfMonth(now.lengthOfMonth())
        .atTime(23, 59, 59)
        .toEpochSecond(ZoneOffset.UTC) * 1000

    return Pair(start, end)
}

