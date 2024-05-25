package presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.*
import java.time.LocalTime

class HomeViewModel : ScreenModel {
    private val _state = MutableStateFlow(dualCore1)
    val state = _state.asStateFlow()

    fun updateSchedule() {
        // todo send updated schedule to databases

    }

    // ui changes related to individual segments
    private fun updateSegment(
        index: Int,
        newStartTime: LocalTime? = null,
        newEndTime: LocalTime? = null
    ) {
        println("got for modification: start time = " + newStartTime.toString() + "; end time = " + newEndTime.toString() + "; index = ${index - 1}")
        println("before modification: " + this.state.value)
        val logicalIndex = index - 1
        _state.update {
            it.copy(
                segments = _state.value.segments.apply {
                    this[logicalIndex] = this[logicalIndex].copy(
                        start = newStartTime ?: this[logicalIndex].start,
                        end = newEndTime ?: this[logicalIndex].end
                    )
                }
            )
        }

        println()
        println("after modification: " + this.state.value)
    }

    fun updateSegmentStartTime(index: Int, newStartTime: LocalTime) {
        updateSegment(index, newStartTime = newStartTime)
    }


    fun updateSegmentEndTime(index: Int, newEndTime: LocalTime) {
        updateSegment(index, newEndTime = newEndTime)
    }
}