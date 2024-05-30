package presentation.ui.daily_stats

import cafe.adriel.voyager.core.model.ScreenModel
import domain.repository.SegmentReportRepository

class DailyStatsViewModel(private val segmentReportRepository: SegmentReportRepository) :
    ScreenModel {

//    private var _currentDataReports = MutableStateFlow(getSegmentReports(LocalDate.now()))
//    val currentDataReports = _currentDataReports.asStateFlow()
//

//    fun getSegmentReports(day: LocalDate): List<SegmentReport> {
//        segmentReportRepository.getReportByDay(day)
//    }

}
