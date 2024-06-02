package domain.model

import java.time.LocalDate
import java.time.LocalTime


data class Segment(
    val start: LocalTime,
    // todo
    // var here until no SegmentUi model in presentation layer created and consumed by DayOverview component
    var end: LocalTime,
    val day: LocalDate? = null
)