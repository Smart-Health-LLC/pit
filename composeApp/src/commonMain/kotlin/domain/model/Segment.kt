package domain.model

import java.time.LocalDate
import java.time.LocalTime


data class Segment(
    val start: LocalTime, val end: LocalTime, val day: LocalDate? = null
)