package presentation.wtf

import java.time.format.DateTimeFormatter

const val timeFormat = "HH:mm"
const val dateFormat = "yyyy/MM/dd"

val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat)

const val CUSTOM_TAG = "PitDev"
