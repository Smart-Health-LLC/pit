package domain.model

import java.time.LocalDate
import java.time.LocalTime

data class SegmentReport(
    val id: Long = 1,
    val day: LocalDate,
    val timeStart: LocalTime,
    val timeEnd: LocalTime,
    val isSkipped: Boolean,
    val wakeUpEaseLevel: Int,
    val fallAsleepEaseLevel: Int
)