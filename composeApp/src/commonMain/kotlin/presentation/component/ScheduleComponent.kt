package presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import domain.model.Segment
import java.time.Duration
import java.time.LocalTime
import kotlin.math.atan2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val minuteInGrad = 0.25
const val startPosInGrads = 270

val backColor = Color(0xff91C3FF)
val frontColor = Color(0xff3775D2)

fun timeToGrad(time: LocalTime): Float {
    val minutes = time.hour * 60 + time.minute
    var grads = startPosInGrads + minutes * minuteInGrad
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}

val randomColors = listOf(
    Color.Red.copy(alpha = 0.4f),
    Color.Blue.copy(alpha = 0.4f),
    Color.Gray.copy(alpha = 0.4f),
    Color.Green.copy(alpha = 0.4f),
    Color.Yellow.copy(alpha = 0.4f),
    Color.Cyan.copy(alpha = 0.4f),
    Color.Magenta.copy(alpha = 0.4f),
)

/**
 * Returns segment duration in minutes taking into account that segment can belongs to different days
 */
fun getDurationBetween(start: LocalTime, end: LocalTime): Duration {
    val test = if (!start.isBefore(end)) {
        Duration
            .ofMinutes(
                Duration.between(start, LocalTime.MAX).toMinutes() +
                        Duration.between(LocalTime.MIN, end).toMinutes()
                        + 1
            )
    } else {
        Duration.between(start, end)
    }
    return test
}

// todo add desktop foundation like and other stuff

fun timeToGrad(minutes: Long): Float {
    var grads = minutes * minuteInGrad
    // kind of nonsense
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
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
    return (angle + 450) % 360
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
    val totalMinutes = (angle / 360 * 24 * 60).toInt()
    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60
    return LocalTime.of(hours, minutes)
}

@Composable
fun ScheduleComponent(
    segments: List<Segment>,
    componentRadius: Int,
    strokeWidth: Float,
    showCurrentTime: Boolean = false,
    useRandomColors: Boolean = false,
    onAddSegment: (Segment) -> Unit = {}
) {
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
    val coroutineScope = rememberCoroutineScope()

    // Creating animation
    val animateFloat = remember { Animatable(0f) }

    val stuff = mutableListOf<Animatable<Float, AnimationVector1D>>()

    repeat(segments.size) {
        stuff.add(remember { Animatable(0f) })
    }

    val colors = mutableListOf<Color>()
    repeat(segments.size) {
        colors.add(randomColors[it % randomColors.size])
    }

    var startAngle by remember { mutableStateOf<Float?>(null) }
    var endAngle by remember { mutableStateOf<Float?>(null) }
    var isDragging by remember { mutableStateOf(false) }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(componentRadius.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val angle = calculateAngle(offset, size)
                        startAngle = angle
                        endAngle = null
                        isDragging = true
                    },
                    onDragEnd = {
                        isDragging = false
                        if (startAngle != null && endAngle != null) {
                            val startTime = angleToLocalTime(startAngle!!)
                            val endTime = angleToLocalTime(endAngle!!)
                            val segment = Segment(start = startTime, end = endTime)
                            onAddSegment(segment)
                        }
                    },
                    onDragCancel = {
                        isDragging = false
                    },
                    onDrag = { change, _ ->
                        if (isDragging) {
                            endAngle = calculateAngle(change.position, size)
                        }
                    }
                )
            }
    ) {
        inset(
            size.width / 2 - componentRadius,
            size.height / 2 - componentRadius
        ) {
            // todo | make pretty animation
            coroutineScope.launch {
                animateFloat.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 0, easing = EaseIn)
                )
            }

            drawCircle(
                color = if (useRandomColors) backColor.copy(alpha = 0.08f) else backColor,
                radius = componentRadius.toFloat() * animateFloat.value,
                center = center,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            )
            if (useRandomColors) {
                drawCircle(
                    color = backColor.copy(alpha = 0.3f),
                    radius = (componentRadius - strokeWidth / 2) * animateFloat.value,
                    center = center,
                    style = Stroke(width = 3f, cap = StrokeCap.Round),
                )
                drawCircle(
                    color = backColor.copy(alpha = 0.3f),
                    radius = (componentRadius + strokeWidth / 2) * animateFloat.value,
                    center = center,
                    style = Stroke(width = 3f, cap = StrokeCap.Round),
                )
            }

            segments.forEachIndexed { index, it ->

                val minutes = getDurationBetween(it.start, it.end).toMinutes()

                // todo | make pretty animation
                coroutineScope.launch {
                    stuff[index].animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 0, easing = EaseIn)
                    )

                }

                drawArc(
                    startAngle = timeToGrad(it.start),
                    sweepAngle = timeToGrad(minutes) * stuff[index].value,
                    useCenter = false,
                    color = if (useRandomColors) colors[index] else frontColor,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
            }

            if (isDragging) {
                if (startAngle != null && endAngle != null) {
                    drawArc(
                        startAngle = startAngle!! - 90,
                        sweepAngle = if (endAngle!! >= startAngle!!) {
                            endAngle!! - startAngle!!
                        } else {
                            360 - startAngle!! + endAngle!!
                        },
                        useCenter = false,
                        color = frontColor,
                        style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
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
