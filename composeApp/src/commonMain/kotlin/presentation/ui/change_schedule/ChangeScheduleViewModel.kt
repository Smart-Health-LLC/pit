package presentation.ui.change_schedule

import cafe.adriel.voyager.core.model.ScreenModel
import domain.model.Segment
import io.github.aakira.napier.Napier
import java.time.Duration
import java.time.LocalTime
import kotlin.math.abs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import presentation.CUSTOM_TAG
import presentation.component.getDurationBetween
import presentation.ui.home.dualCore1

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
    ANOMALY_LONG_SEGMENT,
    SEGMENT_ZERO_DURATION
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
        _screenState.value.editableSegments.forEach {
            if (it.end == it.start) {
                addError(ErrorCode.SEGMENT_ZERO_DURATION)
                // no reason to check something else
                return
            }
        }

        removeError(ErrorCode.SEGMENT_ZERO_DURATION)

        // todo maybe separate checks into functions
        val sortedSegments = _screenState.value.editableSegments.sortedBy { it.start }
        val segmentsAmount = sortedSegments.size

        // check overlaps and clipped segments
        for (i in sortedSegments.indices) {
            // if the last segment belongs to just one current day, no checks on overlaps needed anymore
            if (i == segmentsAmount - 1 && sortedSegments[i].start < sortedSegments[i].end) {
                break
            }
            if (sortedSegments[i].end >= sortedSegments[(i + 1) % segmentsAmount].start) {
                addError(ErrorCode.OVERLAP)
                // if overlaps exists no reason to check anything else
                return
            }
        }

        removeError(ErrorCode.OVERLAP)

        // distance between segments detection
        val maxWakeTime = Duration.ofHours(10)
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
        var baseTst: Duration = Duration.ZERO
        _screenState.value.currentBaseSegments.forEach {
            baseTst = baseTst.plus(getDurationBetween(it.start, it.end))
        }

        var newScheduleTst: Duration = Duration.ZERO
        _screenState.value.editableSegments.forEach {
            newScheduleTst = newScheduleTst.plus(getDurationBetween(it.start, it.end))
        }

        val allowedTstDifferenceInPercents = 50
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
                    sortedSegments[i].end
                ) > maxSegmentDuration
            ) {
                Napier.d(tag = CUSTOM_TAG) { "so im gonna tell about huge segment I found : " }
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
            }

            return
        }

        val sortedEditableSegments = _screenState.value.editableSegments.sortedBy { it.start }
        val sortedBaseSegments = _screenState.value.currentBaseSegments.sortedBy { it.start }

        var isEdited = false

        for (i in sortedEditableSegments.indices) {
            // so edited
            if (sortedBaseSegments[i] != sortedEditableSegments[i]) {
                isEdited = true
                break
            }
        }

        if (isEdited) {
            if (_screenState.value.newScheduleState != NewScheduleState.EDITED) { // don't update if already marked as edited

                _screenState.update {
                    it.copy(
                        newScheduleState = NewScheduleState.EDITED
                    )
                }
            }
        } else {
            if (_screenState.value.newScheduleState != NewScheduleState.MATCH_CURRENT) { // don't update if already marked as matching current
                _screenState.update {
                    it.copy(
                        newScheduleState = NewScheduleState.MATCH_CURRENT
                    )
                }
            }
        }
    }

    private fun addError(error: ErrorCode) {
        if (_screenState.value.errors.contains(error)) {
            return
        } else {
            _screenState.update {
                val savedList = it.errors.toMutableList()
                savedList.add(error)
                it.copy(
                    errors = savedList
                )
            }
        }
    }

    private fun removeError(error: ErrorCode) {
        _screenState.update {
            val savedList = it.errors.toMutableList()
            savedList.removeAll(listOf(error))
            it.copy(
                errors = savedList
            )
        }
    }

    fun updateSegmentStartTime(segment: Segment, newStartTime: LocalTime) {
        _screenState.update {
            val segmentIndex = it.editableSegments.indexOf(segment)
            val savedList = it.editableSegments.toMutableList()
            savedList[segmentIndex] = it.editableSegments[segmentIndex].copy(
                start = newStartTime
            )
            it.copy(
                editableSegments = savedList
            )
        }

        validateSchedule()
        determineWhetherScheduleEdited()
    }

    fun updateSegmentEndTime(segment: Segment, newEndTime: LocalTime) {
        _screenState.update {
            val segmentIndex = it.editableSegments.indexOf(segment)
            val savedList = it.editableSegments.toMutableList()
            savedList[segmentIndex] = it.editableSegments[segmentIndex].copy(
                end = newEndTime
            )

            it.copy(
                editableSegments = savedList
            )
        }

        validateSchedule()
        determineWhetherScheduleEdited()
    }

    fun updateSegmentStartTimeAndEndTime(
        segment: Segment,
        newStartTime: LocalTime,
        newEndTime: LocalTime
    ): Segment {
        val state = _screenState.value

        val segmentIndex = state.editableSegments.indexOf(segment)
        val savedList = state.editableSegments.toMutableList()

        savedList[segmentIndex] = state.editableSegments[segmentIndex].copy(
            start = newStartTime,
            end = newEndTime
        )

        _screenState.tryEmit(state.copy(editableSegments = savedList))

        validateSchedule()
        determineWhetherScheduleEdited()

        return savedList[segmentIndex]
    }

    fun addSegment(segment: Segment): Segment {
        val state = _screenState.value

        val newSegments = state.editableSegments.toMutableList()
        newSegments.add(segment)

        _screenState.tryEmit(state.copy(editableSegments = newSegments))

        validateSchedule()
        determineWhetherScheduleEdited()

        return segment
    }

    fun deleteSegment(segment: Segment) {
        _screenState.update {
            val savedList = it.editableSegments.toMutableList()
            savedList.remove(segment)
            it.copy(editableSegments = savedList)
        }
        validateSchedule()
        determineWhetherScheduleEdited()
    }
}
