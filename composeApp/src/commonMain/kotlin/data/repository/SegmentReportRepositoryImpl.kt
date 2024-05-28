package data.repository

import app.cash.sqldelight.coroutines.asFlow
import com.smarthealth.pit.database.PitDatabase
import data.local.*
import domain.model.SegmentReport
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

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

    override fun getReport(id: Int): Flow<SegmentReport?> {
        TODO("Not yet implemented")
    }

    override fun getReportByDay(day: LocalDate): Flow<SegmentReport?> {
        TODO("Not yet implemented")
    }

    override suspend fun addReport(segmentReport: SegmentReport) {
        segmentReport.toSegmentReportLocalEntity().let {
            dbQuery.insertReport(
                timeStart = it.timeStart,
                timeEnd = it.timeEnd,
                wakeUpEaseLevel = it.wakeUpEaseLevel,
                fallAsleepEaseLevel = it.fallAsleepEaseLevel,
                isSkipped = it.isSkipped,
                day = it.day
            )
        }
    }

    override suspend fun updateReport(segmentReport: SegmentReport) {
        TODO("Not yet implemented")
    }
}
