package presentation.ui.rate

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.Segment
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.LocalTime

const val MAX_LEVEL = 5

data class SegmentRateInfo(
    val isSegmentSkipped: Boolean = false,

    val isSegmentDiffersFromScheduled: Boolean = false,
    val newStartTime: LocalTime? = null,
    val newEndTime: LocalTime? = null,

    val wakeApEaseLevel: Int = 3, // middle of MAX_LEVEL
    val fallAsleepEaseLevel: Int = 3, // middle of MAX_LEVEL

    val note: String? = null,

    val isFormComplete: Boolean = true
)

class RateSegmentViewModel : ScreenModel {
    // todo get data about current segment

    val thisSegmentInfo = Segment(
        start = LocalTime.of(21, 30),
        end = LocalTime.of(0, 50),
        day = LocalDate.of(2024, 5, 21)
    )

    private val _state = MutableStateFlow(SegmentRateInfo())
    val state = _state.asStateFlow()


    private fun isNewSegmentTimesCorrect(): Boolean {
        if (state.value.newStartTime != null && state.value.newEndTime != null) {
            // start time can be more than end time so only
            // equality has to be checked and
            // new any of new time edges have to be differ from ideal ones
            if (state.value.newStartTime != state.value.newEndTime &&
                (state.value.newStartTime != thisSegmentInfo.start || state.value.newEndTime != thisSegmentInfo.end)
            ) {
                return true
            }
        }
        return false
    }

    private fun updateFormCompletionStatus() {
        // if real segment time differ from the schedule's one and new time edges is incorrect
        // the form is not completed
        if (state.value.isSegmentDiffersFromScheduled && !isNewSegmentTimesCorrect()) {
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

    fun toggleSkippedStatus() {
        _state.update {
            it.copy(isSegmentSkipped = !it.isSegmentSkipped)
        }
        updateFormCompletionStatus()
    }

    fun toggleDifferFromScheduleStatus() {
        _state.update {
            it.copy(isSegmentDiffersFromScheduled = !it.isSegmentDiffersFromScheduled)
        }
        updateFormCompletionStatus()
    }

    fun updateNote(content: String) {
        _state.update {
            it.copy(note = content)
        }
    }


    fun updateStartTime(newStartTime: LocalTime) {
        _state.update {
            it.copy(newStartTime = newStartTime)
        }
        updateFormCompletionStatus()
    }


    fun updateEndTime(newEndTime: LocalTime) {
        _state.update {
            it.copy(newEndTime = newEndTime)
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
        // todo save provided data
        println("hahah I'm gonna save new data to sqdelight")
    }
}
