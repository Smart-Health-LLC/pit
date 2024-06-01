package presentation.ui.rate

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.model.Segment
import domain.model.SegmentReport
import domain.repository.SegmentReportRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

data class SegmentRateInfo(
    // by default it is considered standard short nap
    val end: LocalTime = LocalTime.now(),
    val start: LocalTime = end.minusMinutes(25),

    val wakeApEaseLevel: Int = 3, // middle of MAX_LEVEL
    val fallAsleepEaseLevel: Int = 3, // middle of MAX_LEVEL

    val note: String? = null,

    // overlaps is not supported now (I don't understand how is it possible to sleep twice in one period of time)
    val isFormComplete: Boolean = true
)

class RateSegmentViewModel(private val segmentReportRepository: SegmentReportRepository) :
    ScreenModel {
    // todo get data about current segment

    val thisSegmentInfo = Segment(
        start = LocalTime.of(21, 30),
        end = LocalTime.of(0, 50),
        day = LocalDate.of(2024, 5, 21)
    )

    private val _state = MutableStateFlow(SegmentRateInfo())
    val state = _state.asStateFlow()


    private fun isSegmentCorrect(): Boolean {
        // start time can be more than end time so only
        // equality has to be checked and
        // new any of new time edges have to be differ from ideal ones
        if (state.value.start != state.value.end) {
            return true
        }
        // todo check overlaps with existing real segments
        return false
    }

    private fun updateFormCompletionStatus() {
        if (!isSegmentCorrect()) {
            if (state.value.isFormComplete) {
                _state.update {
                    it.copy(isFormComplete = false)
                }
            }
        }

        if (!state.value.isFormComplete) {
            _state.update {
                it.copy(isFormComplete = true)
            }
        }
    }

    fun updateStartTime(newStartTime: LocalTime) {
        _state.update {
            it.copy(start = newStartTime)
        }
        updateFormCompletionStatus()
    }

    fun updateEndTime(newEndTime: LocalTime) {
        _state.update {
            it.copy(end = newEndTime)
        }
        updateFormCompletionStatus()
    }

    fun updateWakeUpEaseLevel(level: Int) {
        _state.update {
            it.copy(wakeApEaseLevel = level)
        }
    }

    fun updateFallAsleepEaseLevel(level: Int) {
        _state.update {
            it.copy(fallAsleepEaseLevel = level)
        }
    }

    fun onSavePressed() {
        screenModelScope.launch {
            segmentReportRepository.addReport(
                SegmentReport(
                    day = thisSegmentInfo.day ?: LocalDate.now(),
                    fallAsleepEaseLevel = state.value.fallAsleepEaseLevel,
                    wakeUpEaseLevel = state.value.wakeApEaseLevel,
                    start = state.value.start,
                    end = state.value.end
                )
            )
        }
    }
}
