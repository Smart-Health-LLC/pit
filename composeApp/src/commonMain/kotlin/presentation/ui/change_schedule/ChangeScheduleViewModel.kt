package presentation.ui.change_schedule

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.Segment
import kotlinx.coroutines.flow.*
import presentation.component.getDurationBetween
import presentation.ui.home.dualCore1
import java.time.Duration
import java.time.LocalTime
import kotlin.math.abs

data class ChangeScheduleScreenState(
    val currentBaseSegments: List<Segment> = dualCore1.segments,
    val editableSegments: MutableList<Segment> = dualCore1.segments,
    val errors: MutableList<ErrorCode> = mutableListOf(),
    val newScheduleState: NewScheduleState = NewScheduleState.MATCH_CURRENT
)

enum class ErrorCode {
    OVERLAP,
    HUGE_AWAKE_TIME,
    TST_STRONGLY_DIFFERS_FROM_BASE,
    ANOMALY_LONG_SEGMENT
}

// that insanely stupid, because it is just fancy names for true\false but it easier to understand
// in nested if conditions
enum class NewScheduleState {
    MATCH_CURRENT,
    EDITED
}

class ChangeScheduleViewModel : ScreenModel {
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

    private fun validateSchedule() {

        // todo maybe separate checks into functions
        val sortedSegments = _screenState.value.editableSegments.sortedBy { it.start }
        val segmentsAmount = sortedSegments.size

        // check overlaps and clipped segments
        var isOverlapsDetected = false
        for (i in sortedSegments.indices) {
            if (sortedSegments[i].end >= sortedSegments[(i + 1) % segmentsAmount].start) {
                addError(ErrorCode.OVERLAP)
                isOverlapsDetected = true
                break
            }
        }

        if (!isOverlapsDetected) {
            removeError(ErrorCode.OVERLAP)
        }


        // distance between segments detection
        val maxWakeTime = Duration.ofHours(6)
        var isHugeAwakeTimeDetected = false

        for (i in sortedSegments.indices) {
            if (getDurationBetween(
                    sortedSegments[i].end,
                    sortedSegments[(i + 1) % segmentsAmount].start
                ) > maxWakeTime
            ) {
                addError(ErrorCode.HUGE_AWAKE_TIME)
                isHugeAwakeTimeDetected = true
                break
            }
        }

        if (!isHugeAwakeTimeDetected) {
            removeError(ErrorCode.HUGE_AWAKE_TIME)
        }


        // tst check
        val baseTst: Duration = Duration.ZERO
        _screenState.value.currentBaseSegments.forEach {
            baseTst.plus(getDurationBetween(it.start, it.end))
        }

        val newScheduleTst: Duration = Duration.ZERO
        _screenState.value.editableSegments.forEach {
            newScheduleTst.plus(getDurationBetween(it.start, it.end))
        }

        val allowedTstDifferenceInPercents = 10
        if (
            abs(baseTst.toMinutes() - newScheduleTst.toMinutes()) /
            /* ------------------------------------------------- */
            /*             */baseTst.toMinutes() /*                        */ * 100
            > allowedTstDifferenceInPercents
        ) {
            addError(ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE)
        } else {
            removeError(ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE)
        }


        // check anomaly segment duration
        val maxSegmentDuration = Duration.ofHours(8)
        var isAnomalySegmentDurationDetected = false

        for (i in sortedSegments.indices) {
            if (getDurationBetween(
                    sortedSegments[i].start,
                    sortedSegments[(i + 1) % segmentsAmount].end
                ) > maxSegmentDuration
            ) {
                addError(ErrorCode.ANOMALY_LONG_SEGMENT)
                isAnomalySegmentDurationDetected = true
                break
            }
        }

        if (!isAnomalySegmentDurationDetected) {
            removeError(ErrorCode.ANOMALY_LONG_SEGMENT)
        }
    }

    fun determineWhetherScheduleEdited() {
        // certainly edited
        if (_screenState.value.editableSegments.size != _screenState.value.currentBaseSegments.size) {
            if (_screenState.value.newScheduleState != NewScheduleState.EDITED) { // don't update if already marked as edited

                _screenState.update {
                    it.copy(
                        newScheduleState = NewScheduleState.EDITED
                    )
                }

                return

            }
        }

        val sortedEditableSegments = _screenState.value.editableSegments.sortedBy { it.start }
        val sortedBaseSegments = _screenState.value.currentBaseSegments.sortedBy { it.start }


        for (i in sortedEditableSegments.indices) {
            // so edited
            if (sortedBaseSegments[i] != sortedEditableSegments[i])

                if (_screenState.value.newScheduleState != NewScheduleState.EDITED) { // don't update if already marked as edited

                    _screenState.update {
                        it.copy(
                            newScheduleState = NewScheduleState.EDITED
                        )
                    }

                    return

                }
        }


        // now edited
        if (_screenState.value.newScheduleState != NewScheduleState.MATCH_CURRENT) { // don't update if already marked as matching current

            _screenState.update {
                it.copy(
                    newScheduleState = NewScheduleState.MATCH_CURRENT
                )
            }

        }
    }


    private fun addError(error: ErrorCode) {
        if (_screenState.value.errors.contains(error)) {
            return
        } else {
            _screenState.value.errors.add(error)
        }
    }


    private fun removeError(error: ErrorCode) {
        _screenState.value.errors.removeAll(listOf(error))
    }


    fun updateSegmentStartTime(segment: Segment, newStartTime: LocalTime) {

        _screenState.update {
            val indexSegment = it.editableSegments.indexOf(segment)
            if (indexSegment != -1) {
                it.editableSegments[indexSegment] =
                    it.editableSegments[indexSegment].copy(start = newStartTime)
            }
            it
        }
    }

    fun updateSegmentEndTime(segment: Segment, newEndTime: LocalTime) {

        // i'll try indexOf() and set()
        // and map and replace whole list

        // try to use indexOf() and set()
        _screenState.update {
            val indexSegment = it.editableSegments.indexOf(segment)
            if (indexSegment != -1) {
                it.editableSegments[indexSegment] =
                    it.editableSegments[indexSegment].copy(end = newEndTime)
            }
            it
        }
        validateSchedule()
//        editedSegment = _screenState.value.editableSegments.find { it.start == segment.start && it.end == segment.end }
    }

    fun addSegment(segment: Segment) {
        _screenState.update {
            it.editableSegments.add(segment)
            it
        }
        validateSchedule()
    }

    fun deleteSegment(segment: Segment) {
        _screenState.update {
            it.editableSegments.remove(segment)
            it
        }
        validateSchedule()
    }
}