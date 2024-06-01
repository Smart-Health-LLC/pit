package presentation.ui.change_schedule

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.Schedule
import domain.model.Segment
import kotlinx.coroutines.flow.*
import presentation.ui.home.dualCore1
import java.time.LocalTime

data class ChangeScheduleScreenState(
    val currentBaseSchedule: Schedule = dualCore1,
    val editableSchedule: Schedule = currentBaseSchedule,
    // error ids is stored here
    val errors: List<Int> = emptyList()
)

class ChangeScheduleViewModel() : ScreenModel {
    private val _screenState = MutableStateFlow(ChangeScheduleScreenState())
    val screenState = _screenState.asStateFlow()


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
//
//        val segmentsToCheck = _screenState.value.segments.toMutableList()
//        segmentsToCheck[logicalIndex] = segmentsToCheck[logicalIndex].copy(
//            start = newStartTime ?: segmentsToCheck[logicalIndex].start,
//            end = newEndTime ?: segmentsToCheck[logicalIndex].end
//        )
//
//        if (!isScheduleAdequate(segmentsToCheck)) {
//            _toastState.update { true }
//        } else {
//            _screenState.update { it.editableSchedule.copy(segments = segmentsToCheck) }
//        }
    }

    fun updateSegmentStartTime(index: Int, newStartTime: LocalTime) {
        updateSegment(index, newStartTime = newStartTime)
    }


    fun updateSegmentEndTime(index: Int, newEndTime: LocalTime) {
        updateSegment(index, newEndTime = newEndTime)
    }


}