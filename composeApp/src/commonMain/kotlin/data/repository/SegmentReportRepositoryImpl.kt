package data.repository

import app.cash.sqldelight.coroutines.asFlow
import com.smarthealth.pit.database.PitDatabase
import data.local.*
import domain.model.SegmentReport
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SegmentReportRepositoryImpl(
    pitDatabase: PitDatabase,
) : SegmentReportRepository {

    private val dbQuery = pitDatabase.segmentReportQueries

    override fun getReports(): Flow<List<SegmentReport>> {
        return dbQuery
            .getAllReports()
            .asFlow()
            .mapToList()
            .map { reports ->
                reports.map {
                    it.toSegmentReport()
                }
            }
    }

    override suspend fun getReportsByDay(day: LocalDate): List<SegmentReport> {
        return dbQuery.getReportsByDay(day.format(DateTimeFormatter.ISO_LOCAL_DATE)).executeAsList()
            .map { it.toSegmentReport() }
    }

    private fun mapStuff(
        id: Long,
        day: String,
        timeStart: String,
        timeEnd: String,
        isSkipped: Boolean,
        wakeUpEaseLevel: Long,
        fallAsleepEaseLevel: Long
    ): SegmentReport {
        return SegmentReport(
            id,
            LocalDate.parse(day),
            LocalTime.parse(timeStart),
            LocalTime.parse(timeEnd),
            wakeUpEaseLevel.toInt(),
            fallAsleepEaseLevel.toInt()
        );
    }

    override fun getReport(id: Int): Flow<SegmentReport?> {
        TODO("Not yet implemented")
    }


    override suspend fun addReport(segmentReport: SegmentReport) {
        segmentReport.toSegmentReportLocalEntity().let {
            dbQuery.insertReport(
                timeStart = it.timeStart,
                timeEnd = it.timeEnd,
                wakeUpEaseLevel = it.wakeUpEaseLevel,
                fallAsleepEaseLevel = it.fallAsleepEaseLevel,
                day = it.day,
                note = it.note
            )
        }
    }


    override suspend fun updateReport(segmentReport: SegmentReport) {
        TODO("Not yet implemented")
    }
}
