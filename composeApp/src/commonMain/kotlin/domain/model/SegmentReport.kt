package domain.model

import java.time.LocalDate
import java.time.LocalTime

data class SegmentReport(
    val id: Long = 1,
    val day: LocalDate,
    val start: LocalTime,
    var end: LocalTime,
    val wakeUpEaseLevel: Int,
    val fallAsleepEaseLevel: Int,
    val note: String? = null
)