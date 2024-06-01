package presentation.ui.daily_stats

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Schedule
import domain.model.SegmentReport
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import presentation.ui.home.dualCore1
import java.time.LocalDate


data class DailyScreenState(
    val selectedDay: LocalDate = LocalDate.now(),
    val dayReports: List<SegmentReport> = emptyList(),
    val currentBaseSchedule: Schedule = dualCore1
)

class DailyStatsViewModel(private val segmentReportRepository: SegmentReportRepository) :
    ScreenModel {

    private val _state = MutableStateFlow(DailyScreenState())
    val state = _state.asStateFlow()
    private var lastDayJob: Job? = null

    init {
        updateReports(state.value.selectedDay)
    }

    fun updateReports(newDay: LocalDate) {
        // cancel previous data request if it was
        _state.update {
            it.copy(
                selectedDay = newDay
            )
        }

        lastDayJob?.cancel()

        // get new data without getReports() because it

        // honestly i'm not sure what to do better
        lastDayJob = screenModelScope.launch {
            val newDayReports = segmentReportRepository.getReportsByDay(newDay)
            _state.update {
                it.copy(
                    dayReports = newDayReports
                )
            }
        }


//        (segmentReportRepository.getReports()).stateIn(
//            scope = screenModelScope,
//            started = SharingStarted.WhileSubscribed(),
//            initialValue = emptyList(),
//        )

    }

//    private var _currentDataReports = MutableStateFlow(getSegmentReports(LocalDate.now()))
//    val currentDataReports = _currentDataReports.asStateFlow()
//

//    fun updateSelectedDay() {
//
//    }

//    fun getSegmentReports(day: LocalDate): List<SegmentReport> {
//        segmentReportRepository.getReportByDay(day)
//    }

}
