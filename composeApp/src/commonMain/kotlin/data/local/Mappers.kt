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

fun SegmentReportLocalEntity.toSegmentReport() = SegmentReport(
    id = id,
    day = LocalDate.parse(day),
    timeStart = LocalTime.parse(timeStart),
    timeEnd = LocalTime.parse(timeEnd),
    isSkipped = isSkipped,
    wakeUpEaseLevel = wakeUpEaseLevel.toInt(),
    fallAsleepEaseLevel = fallAsleepEaseLevel.toInt()
)

fun SegmentReport.toSegmentReportLocalEntity() = SegmentReportLocalEntity(
    id = id,
    day = day.format(DateTimeFormatter.ofPattern(localSQLiteDateTimeFormat)),
    timeStart = timeStart.format(DateTimeFormatter.ofPattern(localSQLiteDateTimeFormat)),
    timeEnd = timeEnd.format(DateTimeFormatter.ofPattern(localSQLiteDateTimeFormat)),
    isSkipped = isSkipped,
    wakeUpEaseLevel = wakeUpEaseLevel.toLong(),
    fallAsleepEaseLevel = fallAsleepEaseLevel.toLong()
)

@JvmOverloads
fun <T : Any> Flow<Query<T>>.mapToList(
    context: CoroutineContext = Dispatchers.Default,
): Flow<List<T>> = map {
    withContext(context) {
        it.executeAsList()
    }
}
