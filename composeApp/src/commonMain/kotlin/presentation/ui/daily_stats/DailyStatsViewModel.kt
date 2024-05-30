package presentation.ui.daily_stats

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.SegmentReport
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class DailyStatsViewModel(private val segmentReportRepository: SegmentReportRepository) :
    ScreenModel {

    private var _currentDataReports = MutableStateFlow(getSegmentReports(LocalDate.now()))
    val currentDataReports = _currentDataReports.asStateFlow()


    fun getSegmentReports(day: LocalDate): List<SegmentReport> {
        segmentReportRepository.getReportByDay(day)
    }

}
