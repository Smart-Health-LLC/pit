package presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.Segment
import java.time.Duration
import java.time.LocalTime
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.round
import kotlin.math.sin
import kotlinx.coroutines.delay

private const val EDITING_ARC_ANGLE = 7.5f

private val backColor = Color(0xff91C3FF)
private val frontColor = Color(0xff3775D2)

private val randomColors = listOf(
    Color.Red.copy(alpha = 0.4f),
    Color.Blue.copy(alpha = 0.4f),
    Color.Gray.copy(alpha = 0.4f),
    Color.Green.copy(alpha = 0.4f),
    Color.Yellow.copy(alpha = 0.4f),
    Color.Cyan.copy(alpha = 0.4f),
    Color.Magenta.copy(alpha = 0.4f)
)

/**
 * Returns segment duration in minutes taking into account that segment can belongs to different days
 */
fun getDurationBetween(start: LocalTime, end: LocalTime): Duration {
    val test = if (!start.isBefore(end)) {
        Duration
            .ofMinutes(
                Duration.between(start, LocalTime.MAX).toMinutes() +
                    Duration.between(LocalTime.MIN, end).toMinutes() +
                    1
            )
    } else {
        Duration.between(start, end)
    }
    return test
}

// todo add desktop foundation like and other stuff

private fun timeToGrad(time: LocalTime): Float {
    val totalMinutes = time.hour * 60 + time.minute
    return (totalMinutes.toFloat() / (24 * 60) * 360)
}

private fun timeToGrad(minutes: Long): Float {
    return (minutes.toFloat() / (24 * 60) * 360)
}

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
 * Converts an angle to a LocalTime object.
 * The angle is first converted to total minutes, considering that 360 degrees represent 24 hours.
 * The total minutes are then divided into hours and minutes to create a LocalTime object.
 *
 * @param angle The angle in degrees to be converted to LocalTime.
 * @return A LocalTime object representing the time corresponding to the given angle.
 */
private fun angleToLocalTime(angle: Float): LocalTime {
    val adjustedAngle = getAdjustedAngle(angle)

    val totalMinutes = round(adjustedAngle / 360f * 24f * 60f).toInt()
    val roundedMinutes = (round(totalMinutes / 5f) * 5).toInt()
    val hours = roundedMinutes / 60 % 24
    val minutes = roundedMinutes % 60

    return LocalTime.of(hours, minutes)
}

/**
 * Calculates the position of a point on the circumference of a circle.
 * The position is calculated using the given angle, radius, and center of the circle.
 * The angle is first converted to radians, which is then used to calculate the x and y coordinates of the point.
 *
 * @param angle The angle in degrees. The angle is measured from the positive x-axis.
 * @param radius The radius of the circle.
 * @param center The center of the circle.
 * @return An Offset object representing the position of the point on the circumference of the circle.
 */
private fun calculateTextPosition(angle: Float, radius: Float, center: Offset): Offset {
    val radians = Math.toRadians(angle.toDouble())
    val x = center.x + radius * cos(radians).toFloat()
    val y = center.y + radius * sin(radians).toFloat()
    return Offset(x, y)
}

/**
 * Adjusts the given angle to ensure it falls within the range of 0 to 360 degrees.
 *
 * This function takes an angle as input and adjusts it to ensure it falls within the range of 0 to 360 degrees.
 * If the angle is less than 0, it adds 360 to it until it falls within the range.
 * If the angle is greater than 360, it subtracts 360 from it until it falls within the range.
 *
 * @param angle The angle in degrees to be adjusted. It can be any float value.
 * @return The adjusted angle, which will be a float value between 0 and 360 degrees.
 */
private fun getAdjustedAngle(angle: Float): Float {
    var adjustedAngle = angle

    while (adjustedAngle !in 0f..360f) {
        adjustedAngle %= 360
        if (adjustedAngle < 0) {
            adjustedAngle += 360
        }
    }

    return adjustedAngle
}

@Composable
fun ScheduleComponent(
    segments: List<Segment>,
    componentRadius: Int,
    strokeWidth: Float,
    showCurrentTime: Boolean = false,
    useRandomColors: Boolean = false,
    onAddSegment: ((Segment) -> Unit)? = null,
    onUpdateSegment: ((Segment, LocalTime, LocalTime) -> Unit)? = null
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

    // Creating / Moving / Editing segment
    var segmentStartAngle by remember { mutableStateOf<Float?>(null) }
    var segmentEndAngle by remember { mutableStateOf<Float?>(null) }

    var isCreatingSegment by remember { mutableStateOf(false) }

    var draggingSegment by remember { mutableStateOf<Segment?>(null) }
    var draggingSegmentInitialAngle by remember { mutableStateOf<Float?>(null) }

    var isEditingSegmentStart by remember { mutableStateOf(false) }
    var isEditingSegmentEnd by remember { mutableStateOf(false) }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(componentRadius.dp)
            .rotate(-90f)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val angle = calculateAngle(offset, size)
                        segmentStartAngle = angle
                        segmentEndAngle = null

                        if (onUpdateSegment != null) {
                            draggingSegment = currentSegments.find {
                                val startAngle = timeToGrad(it.start)
                                val endAngle = timeToGrad(it.end)
                                if (startAngle <= endAngle) {
                                    angle in startAngle..endAngle
                                } else {
                                    angle >= startAngle || angle <= endAngle
                                }
                            }
                        }

                        if (draggingSegment != null) {
                            segmentStartAngle = timeToGrad(draggingSegment!!.start)
                            segmentEndAngle = timeToGrad(draggingSegment!!.end)
                            draggingSegmentInitialAngle = angle
                            if (angle in segmentStartAngle!! -
                                EDITING_ARC_ANGLE..segmentStartAngle!! + EDITING_ARC_ANGLE
                            ) {
                                isEditingSegmentStart = true
                            } else if (angle in segmentEndAngle!! -
                                EDITING_ARC_ANGLE..segmentEndAngle!! + EDITING_ARC_ANGLE
                            ) {
                                isEditingSegmentEnd = true
                            }
                        } else if (onAddSegment != null) {
                            segmentStartAngle = angle
                            segmentEndAngle = null
                            isCreatingSegment = true
                        }
                    },
                    onDrag = { change, _ ->
                        val angle = calculateAngle(change.position, size)

                        if (isCreatingSegment) {
                            segmentEndAngle = angle
                        } else if (draggingSegment != null) {
                            if (isEditingSegmentStart) {
                                segmentStartAngle = angle
                            } else if (isEditingSegmentEnd) {
                                segmentEndAngle = angle
                            } else {
                                val angleDiff = angle - (draggingSegmentInitialAngle ?: 0f)

                                segmentStartAngle = (segmentStartAngle ?: 0f) + angleDiff
                                segmentEndAngle = (segmentEndAngle ?: 0f) + angleDiff
                                draggingSegmentInitialAngle = angle
                            }
                        }
                    },
                    onDragEnd = {
                        isCreatingSegment = false
                        isEditingSegmentStart = false
                        isEditingSegmentEnd = false
                        if (segmentStartAngle != null && segmentEndAngle != null) {
                            val startTime = angleToLocalTime(segmentStartAngle!!)
                            val endTime = angleToLocalTime(segmentEndAngle!!)

                            if (draggingSegment != null) {
                                onUpdateSegment?.invoke(draggingSegment!!, startTime, endTime)
                                draggingSegment = null
                            } else {
                                val segment = Segment(start = startTime, end = endTime)
                                onAddSegment?.invoke(segment)
                            }
                        }
                    },
                    onDragCancel = {
                        isCreatingSegment = false
                        draggingSegment = null
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
                if (it == draggingSegment) return@forEachIndexed

                val minutes = getDurationBetween(it.start, it.end).toMinutes()

                val startAngle = timeToGrad(it.start)
                val sweepAngle = timeToGrad(minutes)

                drawSegment(
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    innerRadius = componentRadius - strokeWidth / 2,
                    center = center,
                    textMeasurer = textMeasurer,
                    useRandomColors = useRandomColors,
                    colors = colors,
                    index = index,
                    strokeWidth = strokeWidth,
                    frontColor = frontColor
                )
            }

            if (isCreatingSegment || draggingSegment != null) {
                if (segmentStartAngle != null && segmentEndAngle != null) {
                    val startAngle = segmentStartAngle!!
                    val sweepAngle = if (segmentEndAngle!! >= segmentStartAngle!!) {
                        segmentEndAngle!! - segmentStartAngle!!
                    } else {
                        360 - segmentStartAngle!! + segmentEndAngle!!
                    }

                    drawSegment(
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        innerRadius = componentRadius - strokeWidth / 2,
                        center = center,
                        textMeasurer = textMeasurer,
                        useRandomColors = false,
                        colors = colors,
                        index = 0,
                        strokeWidth = strokeWidth,
                        frontColor = frontColor
                    )
                }
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
    useRandomColors: Boolean,
    colors: List<Color>,
    index: Int,
    strokeWidth: Float,
    frontColor: Color
) {
    drawArc(
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        color = if (useRandomColors) colors[index] else frontColor,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
    )

    drawArc(
        startAngle = startAngle,
        sweepAngle = EDITING_ARC_ANGLE,
        useCenter = false,
        color = if (useRandomColors) colors[index] else frontColor,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
    )

    drawArc(
        startAngle = startAngle + sweepAngle - EDITING_ARC_ANGLE,
        sweepAngle = EDITING_ARC_ANGLE,
        useCenter = false,
        color = if (useRandomColors) colors[index] else frontColor,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
    )

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

private fun DrawScope.printTimeOnAngle(
    text: String,
    textStyle: TextStyle,
    textMeasurer: TextMeasurer,
    angle: Float,
    innerRadius: Float,
    center: Offset
) {
    val adjustedAngle = getAdjustedAngle(angle)
    var textPosition = calculateTextPosition(angle, innerRadius, center)
    val size = textMeasurer.measure(text = text, style = textStyle).size

    val textHeight = size.height * 1.5f
    val textWidth = size.width * 1.5f

    val offsetX: Float
    val offsetY: Float

    if (round(adjustedAngle) in 0f..89f) {
        val percent = adjustedAngle / 90

        offsetX = -((0.5f * textWidth) + (0.5f * (percent * textWidth)))
        offsetY = -((0.5f * textHeight) - (percent * textHeight))
    } else if (round(adjustedAngle) in 90f..179f) {
        val percent = (adjustedAngle - 90) / 90

        offsetX = -(textWidth - (0.5f * (percent * textWidth)))
        offsetY = (0.5f * textHeight) + (0.5f * (percent * textHeight))
    } else if (round(adjustedAngle) in 180f..269f) {
        val percent = (adjustedAngle - 180) / 90

        offsetX = -((0.5f * textWidth) - (0.8f * (percent * textWidth)))
        offsetY = textHeight - (0.5f * (percent * textHeight))
    } else {
        val percent = (adjustedAngle - 270) / 90

        offsetX = (0.3f * textWidth) - (0.5f * (percent * textWidth))
        offsetY = (0.5f * textHeight) - (percent * textHeight)
    }

    textPosition = Offset(textPosition.x + offsetY, textPosition.y + offsetX)

    withTransform({
        rotate(90f, pivot = textPosition)
    }) {
        drawText(
            textMeasurer = textMeasurer,
            text = angleToLocalTime(angle).toString(),
            topLeft = textPosition,
            style = textStyle,
            size = Size(width = size.width.toFloat(), height = size.height.toFloat())
        )
    }
}

private fun DrawScope.drawCircleWithOutline(
    useRandomColors: Boolean,
    backColor: Color,
    componentRadius: Float,
    center: Offset,
    strokeWidth: Float
) {
    drawCircle(
        color = if (useRandomColors) backColor.copy(alpha = 0.08f) else backColor,
        radius = componentRadius,
        center = center,
        style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
    )
    if (useRandomColors) {
        drawCircle(
            color = backColor.copy(alpha = 0.3f),
            radius = (componentRadius - strokeWidth / 2),
            center = center,
            style = Stroke(width = 3f, cap = StrokeCap.Round)
        )
        drawCircle(
            color = backColor.copy(alpha = 0.3f),
            radius = (componentRadius + strokeWidth / 2),
            center = center,
            style = Stroke(width = 3f, cap = StrokeCap.Round)
        )
    }
}
