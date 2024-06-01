package domain.model

import java.time.LocalDate
import java.time.LocalTime


data class Segment(
    val start: LocalTime,
    // var because of shit happening inside DayOverview component
    var end: LocalTime,
    val day: LocalDate? = null
)