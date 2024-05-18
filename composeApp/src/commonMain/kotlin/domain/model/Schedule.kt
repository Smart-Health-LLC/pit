package domain.model

import java.time.LocalTime


data class Schedule(
    val name: String,
    val tst: LocalTime,
    val segments: List<Segment>
)
