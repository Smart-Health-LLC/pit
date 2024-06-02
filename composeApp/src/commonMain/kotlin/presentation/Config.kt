package presentation

import java.time.format.DateTimeFormatter

const val timeFormat = "HH:mm"
const val userDateFormat = "yyyy-MM-dd"

object UserDateFormats {
    const val variant1 = "MM/dd/yyyy"
    const val variant2 = "dd.MM.y"
    // todo and so on
}

object UserTimeFormats {
    // todo you know
}

val userDateFormmater: DateTimeFormatter = DateTimeFormatter.ofPattern(userDateFormat)

val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat)

const val CUSTOM_TAG = "PitDev"
