package data.local

import app.cash.sqldelight.Query
import database.SegmentReportLocalEntity
import domain.model.SegmentReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.coroutines.CoroutineContext


// @formatter:off
fun SegmentReportLocalEntity.toSegmentReport() = SegmentReport(
    id                  = id,
    day                 = LocalDate.parse(day, DateTimeFormatter.ISO_LOCAL_DATE),
    start           = LocalTime.parse(timeStart, DateTimeFormatter.ISO_LOCAL_TIME),
    end             = LocalTime.parse(timeEnd, DateTimeFormatter.ISO_LOCAL_TIME),
    wakeUpEaseLevel     = wakeUpEaseLevel.toInt(),
    fallAsleepEaseLevel = fallAsleepEaseLevel.toInt()
)

fun SegmentReport.toSegmentReportLocalEntity() = SegmentReportLocalEntity(
    id                  = id,
    day                 = day.format(DateTimeFormatter.ISO_LOCAL_DATE),
    timeStart           = start.format(DateTimeFormatter.ISO_LOCAL_TIME),
    timeEnd             = end.format(DateTimeFormatter.ISO_LOCAL_TIME),
    wakeUpEaseLevel     = wakeUpEaseLevel.toLong(),
    fallAsleepEaseLevel = fallAsleepEaseLevel.toLong(),
    note                = note
)
// @formatter:on

@JvmOverloads
fun <T : Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = Dispatchers.Default,
): Flow<List<T>> = map {
    withContext(context) {
        it.executeAsList()
    }
}
