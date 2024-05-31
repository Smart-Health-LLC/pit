package presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.Schedule
import domain.model.Segment
import kotlinx.coroutines.flow.*
import java.time.LocalTime

data class HomeState(
    val currentSchedule: Schedule = dualCore1,
    val currentStreak: Int
)

class HomeViewModel : ScreenModel {
    private val _state = MutableStateFlow(dualCore1)
    val state = _state.asStateFlow()

    private val _toastState = MutableStateFlow(false)

    // todo add state info to have more control of toast
    val toastState = _toastState.asStateFlow()

    fun updateSchedule() {
        // todo send updated schedule to databases
    }

    fun updateToastState(flag: Boolean) {
        _toastState.update { flag }
    }

    private fun isScheduleAdequate(segments: List<Segment>): Boolean {
        // check overlaps and clipped segments
        // ------ sort segments by its start time
        // ------ check if next segment's end time lies after the following segment's start time
        val sortedSegments = segments.sortedBy { it.start }
        for (i in 0..segments.size - 2) {
            if (sortedSegments[i].end >= sortedSegments[i + 1].start) {
                return false
            }
        }
        return true
    }

    // ui changes related to individual segments
    private fun updateSegment(
        index: Int,
        newStartTime: LocalTime? = null,
        newEndTime: LocalTime? = null
    ) {
        val logicalIndex = index - 1

        val segmentsToCheck = _state.value.segments.toMutableList()
        segmentsToCheck[logicalIndex] = segmentsToCheck[logicalIndex].copy(
            start = newStartTime ?: segmentsToCheck[logicalIndex].start,
            end = newEndTime ?: segmentsToCheck[logicalIndex].end
        )

        if (!isScheduleAdequate(segmentsToCheck)) {
            _toastState.update { true }
        } else {
            _state.update { it.copy(segments = segmentsToCheck) }
        }
    }

    fun updateSegmentStartTime(index: Int, newStartTime: LocalTime) {
        updateSegment(index, newStartTime = newStartTime)
    }


    fun updateSegmentEndTime(index: Int, newEndTime: LocalTime) {
        updateSegment(index, newEndTime = newEndTime)
    }
}