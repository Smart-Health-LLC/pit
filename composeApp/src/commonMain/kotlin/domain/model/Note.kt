package domain.model

import kotlinx.datetime.DateTimeUnit

data class Note(
    val content: String,
    val dateTimeCreated: DateTimeUnit
)