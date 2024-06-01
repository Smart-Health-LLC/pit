package presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Schedule
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.flow.*
import java.time.Duration
import java.time.LocalTime

data class HomeState(
    val currentSchedule: Schedule = dualCore1, // to show on the circle

    // to show on the summary block
    val napIn: Duration = Duration.ZERO,
    val tstToday: LocalTime = LocalTime.now(),
    val recordDays: Int = 0
)

class HomeViewModel(private val segmentReportRepository: SegmentReportRepository) : ScreenModel {
    private val _newState = MutableStateFlow(HomeState())
    val newState = _newState.asStateFlow()

    init {
        updateNapIn(dualCore1)
    }


    // piece of shit but no time left to make it good
    var reports = (segmentReportRepository.getReports()).stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )


    fun updateNapIn(schedule: Schedule) {

        val now = LocalTime.now()
        val sortedSegments = schedule.segments.sortedBy { it.start }

        sortedSegments.forEach { segment ->
            if (segment.start > now) {
                _newState.update {
                    it.copy(
                        napIn = Duration.between(now, segment.start)
                    )
                }
                return
            }
        }
    }


    private val _state = MutableStateFlow(dualCore1)
    val state = _state.asStateFlow()


}