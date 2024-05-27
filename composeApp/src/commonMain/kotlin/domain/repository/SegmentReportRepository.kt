package domain.repository

import domain.model.SegmentReport
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface SegmentReportRepository {
    fun getReports(): Flow<List<SegmentReport>>
    fun getReport(id: Int): Flow<SegmentReport?>
    fun getReportByDay(day: LocalDate): Flow<SegmentReport?>
    suspend fun addReport(segmentReport: SegmentReport)
    suspend fun updateReport(segmentReport: SegmentReport)
}