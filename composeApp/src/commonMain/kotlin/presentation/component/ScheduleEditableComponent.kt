package presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Segment
import java.time.LocalTime
import kotlin.math.atan2
import kotlinx.coroutines.delay

private const val ARC_EDGE = 7.5f

/**
 * Calculates the angle between the center of the canvas and the given offset.
 * The angle is calculated using the atan2 function and then converted to degrees.
 * The result is adjusted to ensure it falls within the range of 0 to 360 degrees.
 *
 * @param offset The offset for which the angle is to be calculated. It represents the position of the touch event on the screen.
 * @param size The size of the canvas. It is used to calculate the center of the canvas.
 * @return The calculated angle in degrees.
 */
private fun calculateAngle(offset: Offset, size: IntSize): Float {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val angle = Math.toDegrees(
        atan2((offset.y - centerY).toDouble(), (offset.x - centerX).toDouble())
    ).toFloat()

    return getAdjustedAngle(angle)
}

/**
 * This function calculates the sweep angle for a segment on a circular schedule component.
 *
 * @param startAngle The start angle of the segment in degrees. It should be a float value between 0 and 360.
 * @param endAngle The end angle of the segment in degrees. It should be a float value between 0 and 360.
 * @return The sweep angle of the segment in degrees. It will be a float value between 0 and 360.
 */
private fun calculateSweepAngle(startAngle: Float, endAngle: Float): Float {
    return if (endAngle >= startAngle) {
        endAngle - startAngle
    } else {
        360 - startAngle + endAngle
    }
}

private sealed class SegmentState {
    data object None : SegmentState()
    data class Selected(val segment: Segment) : SegmentState()
    data class Creating(val startAngle: Float, var endAngle: Float) : SegmentState()
    data class Moving(
        val segment: Segment,
        val initialAngle: Float,
        var newStartAngle: Float,
        var newEndAngle: Float
    ) : SegmentState()

    data class EditingStart(val segment: Segment, val newStartAngle: Float) : SegmentState()
    data class EditingEnd(val segment: Segment, val newEndAngle: Float) : SegmentState()
}

@Composable
fun ScheduleEditableComponent(
    segments: List<Segment>,
    componentRadius: Int,
    strokeWidth: Float,
    showCurrentTime: Boolean = false,
    useRandomColors: Boolean = false,
    onAddSegment: (Segment) -> Segment,
    onUpdateSegment: (Segment, LocalTime, LocalTime) -> Segment,
    onDeleteSegment: (Segment) -> Unit
) {
    val textMeasurer = rememberTextMeasurer()
    val currentSegments by rememberUpdatedState(segments)
    var currentTime by remember { mutableStateOf(LocalTime.now()) }

    if (showCurrentTime) {
        val updateIntervalMinutes = 5L
        LaunchedEffect(updateIntervalMinutes) {
            while (true) {
                delay(updateIntervalMinutes * 60 * 1000) // Convert minutes to milliseconds
                currentTime = currentTime.plusMinutes(5)
            }
        }
    }

    val colors = mutableListOf<Color>()
    repeat(segments.size) {
        colors.add(randomColors[it % randomColors.size])
    }

    var segmentState by remember { mutableStateOf<SegmentState>(SegmentState.None) }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(componentRadius.dp)
            .rotate(-90f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset: Offset ->
                        val angle = calculateAngle(offset, size)

                        val selectedSegment = currentSegments.find {
                            val startAngle = timeToGrad(it.start)
                            val endAngle = timeToGrad(it.end)
                            if (startAngle <= endAngle) {
                                angle in startAngle..endAngle
                            } else {
                                angle >= startAngle || angle <= endAngle
                            }
                        }

                        segmentState = if (selectedSegment != null) {
                            SegmentState.Selected(selectedSegment)
                        } else {
                            SegmentState.None
                        }
                    },
                    onLongPress = { offset: Offset ->
                        val angle = calculateAngle(offset, size)

                        val selectedSegment = currentSegments.find {
                            val startAngle = timeToGrad(it.start)
                            val endAngle = timeToGrad(it.end)
                            if (startAngle <= endAngle) {
                                angle in startAngle..endAngle
                            } else {
                                angle >= startAngle || angle <= endAngle
                            }
                        }

                        if (selectedSegment != null) {
                            onDeleteSegment.invoke(selectedSegment)
                        }

                        segmentState = SegmentState.None
                    }
                )
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val angle = calculateAngle(offset, size)

                        val selectedSegment = currentSegments.find {
                            val startAngle = timeToGrad(it.start)
                            val endAngle = timeToGrad(it.end)
                            if (startAngle <= endAngle) {
                                angle in startAngle..endAngle
                            } else {
                                angle >= startAngle || angle <= endAngle
                            }
                        }

                        segmentState = if (selectedSegment != null) {
                            SegmentState.Selected(selectedSegment)
                        } else {
                            SegmentState.Creating(
                                startAngle = angle,
                                endAngle = angle
                            )
                        }
                    },
                    onDrag = { change, _ ->
                        val angle = calculateAngle(change.position, size)
                        when (val state = segmentState) {
                            is SegmentState.Creating -> {
                                segmentState = state.copy(endAngle = angle)
                            }

                            is SegmentState.Selected -> {
                                val startAngle = timeToGrad(state.segment.start)
                                val endAngle = timeToGrad(state.segment.end)

                                when (angle) {
                                    in startAngle - ARC_EDGE..startAngle + ARC_EDGE -> {
                                        segmentState = SegmentState.EditingStart(
                                            segment = state.segment,
                                            newStartAngle = angle
                                        )
                                    }

                                    in endAngle - ARC_EDGE..endAngle + ARC_EDGE -> {
                                        segmentState = SegmentState.EditingEnd(
                                            segment = state.segment,
                                            newEndAngle = angle
                                        )
                                    }

                                    else -> {
                                        segmentState = SegmentState.Moving(
                                            segment = state.segment,
                                            initialAngle = angle,
                                            newEndAngle = timeToGrad(state.segment.end),
                                            newStartAngle = timeToGrad(state.segment.start)
                                        )
                                    }
                                }
                            }

                            is SegmentState.Moving -> {
                                val angleDiff = angle - state.initialAngle
                                val startTime = timeToGrad(state.segment.start) + angleDiff
                                val endTime = timeToGrad(state.segment.end) + angleDiff

                                segmentState = state.copy(
                                    newEndAngle = endTime,
                                    newStartAngle = startTime
                                )
                            }

                            is SegmentState.EditingStart -> {
                                segmentState = state.copy(
                                    newStartAngle = angle
                                )
                            }

                            is SegmentState.EditingEnd -> {
                                segmentState = state.copy(
                                    newEndAngle = angle
                                )
                            }

                            SegmentState.None -> {}
                        }
                    },
                    onDragEnd = {
                        when (val state = segmentState) {
                            is SegmentState.Creating -> {
                                val segment = Segment(
                                    start = angleToLocalTime(state.startAngle),
                                    end = angleToLocalTime(state.endAngle)
                                )
                                onAddSegment.invoke(segment)
                                segmentState = SegmentState.Selected(segment)
                            }

                            is SegmentState.Moving -> {
                                val segment = onUpdateSegment.invoke(
                                    state.segment,
                                    angleToLocalTime(state.newStartAngle),
                                    angleToLocalTime(state.newEndAngle)
                                )
                                segmentState = SegmentState.Selected(segment)
                            }

                            is SegmentState.EditingStart -> {
                                val segment = onUpdateSegment.invoke(
                                    state.segment,
                                    angleToLocalTime(state.newStartAngle),
                                    state.segment.end
                                )
                                segmentState = SegmentState.Selected(segment)
                            }

                            is SegmentState.EditingEnd -> {
                                val segment = onUpdateSegment.invoke(
                                    state.segment,
                                    state.segment.start,
                                    angleToLocalTime(state.newEndAngle)
                                )
                                segmentState = SegmentState.Selected(segment)
                            }

                            else -> {
                                segmentState = SegmentState.None
                            }
                        }
                    }
                )
            }
    ) {
        inset(
            size.width / 2 - componentRadius,
            size.height / 2 - componentRadius
        ) {
            drawCircleWithOutline(
                useRandomColors = useRandomColors,
                backColor = backColor,
                componentRadius = componentRadius.toFloat(),
                center = center,
                strokeWidth = strokeWidth
            )

            segments.forEachIndexed { index, it ->
                when (val state = segmentState) {
                    is SegmentState.Moving -> {
                        if (state.segment == it) {
                            return@forEachIndexed
                        }
                    }

                    is SegmentState.EditingStart -> {
                        if (state.segment == it) {
                            return@forEachIndexed
                        }
                    }

                    is SegmentState.EditingEnd -> {
                        if (state.segment == it) {
                            return@forEachIndexed
                        }
                    }

                    is SegmentState.Selected -> {
                        if (state.segment == it) {
                            return@forEachIndexed
                        }
                    }

                    else -> {}
                }

                val minutes = getDurationBetween(it.start, it.end).toMinutes()

                val startAngle = timeToGrad(it.start)
                val sweepAngle = timeToGrad(minutes)

                drawSegment(
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    innerRadius = componentRadius - strokeWidth / 2,
                    center = center,
                    textMeasurer = textMeasurer,
                    color = if (useRandomColors) colors[index] else frontColor,
                    strokeWidth = strokeWidth,
                    isSelected = false
                )
            }

            when (val state = segmentState) {
                is SegmentState.Selected -> {
                    val minutes =
                        getDurationBetween(state.segment.start, state.segment.end).toMinutes()

                    val startAngle = timeToGrad(state.segment.start)
                    val sweepAngle = timeToGrad(minutes)

                    drawSegment(
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        color = frontColor.copy(alpha = 0.3f),
                        strokeWidth = strokeWidth,
                        isSelected = true
                    )
                }

                is SegmentState.Creating -> {
                    val sweepAngle = calculateSweepAngle(state.startAngle, state.endAngle)

                    drawSegment(
                        startAngle = state.startAngle,
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        color = frontColor.copy(alpha = 0.3f),
                        strokeWidth = strokeWidth,
                        isSelected = false
                    )
                }

                is SegmentState.Moving -> {
                    val sweepAngle = calculateSweepAngle(state.newStartAngle, state.newEndAngle)

                    drawSegment(
                        startAngle = state.newStartAngle,
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        color = frontColor.copy(alpha = 0.3f),
                        strokeWidth = strokeWidth,
                        isSelected = false
                    )
                }

                is SegmentState.EditingStart -> {
                    val sweepAngle =
                        calculateSweepAngle(state.newStartAngle, timeToGrad(state.segment.end))

                    drawSegment(
                        startAngle = state.newStartAngle,
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        color = frontColor.copy(alpha = 0.3f),
                        strokeWidth = strokeWidth,
                        isSelected = true
                    )
                }

                is SegmentState.EditingEnd -> {
                    val sweepAngle =
                        calculateSweepAngle(timeToGrad(state.segment.start), state.newEndAngle)

                    drawSegment(
                        startAngle = timeToGrad(state.segment.start),
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        color = frontColor.copy(alpha = 0.3f),
                        strokeWidth = strokeWidth,
                        isSelected = true
                    )
                }

                else -> {}
            }

            if (showCurrentTime) {
                drawArc(
                    startAngle = timeToGrad(currentTime),
                    sweepAngle = timeToGrad(5),
                    useCenter = false,
                    color = Color.Red,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
            }
        }
    }
}

private fun DrawScope.drawSegment(
    startAngle: Float,
    sweepAngle: Float,
    innerRadius: Float,
    center: Offset,
    textMeasurer: TextMeasurer,
    color: Color,
    strokeWidth: Float,
    isSelected: Boolean
) {
    drawArc(
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        color = color,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
    )

    if (isSelected) {
        drawArc(
            startAngle = startAngle,
            sweepAngle = ARC_EDGE,
            useCenter = false,
            color = color,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
        )

        drawArc(
            startAngle = startAngle + sweepAngle - ARC_EDGE,
            sweepAngle = ARC_EDGE,
            useCenter = false,
            color = color,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
        )
    }

    printTimeOnAngle(
        text = angleToLocalTime(startAngle).toString(),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 11.sp
        ),
        textMeasurer = textMeasurer,
        angle = startAngle,
        innerRadius = innerRadius,
        center = center
    )

    printTimeOnAngle(
        text = angleToLocalTime(startAngle + sweepAngle).toString(),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 11.sp
        ),
        textMeasurer = textMeasurer,
        angle = startAngle + sweepAngle,
        innerRadius = innerRadius,
        center = center
    )
}
