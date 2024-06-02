package presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp
import domain.model.Segment
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalTime

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

/**
 * Returns segment duration in minutes taking into account that segment can belongs to different days
 */
fun getDurationBetween(start: LocalTime, end: LocalTime): Duration {
    return if (!start.isBefore(end)) {
        Duration
            .ofMinutes(
                Duration.between(start, LocalTime.MAX).toMinutes() +
                        Duration.between(LocalTime.MIN, end).toMinutes()
                        + 1
            )
    } else {
        Duration.between(start, end)
    }
}

// todo add desktop foundation like and other stuff

fun timeToGrad(minutes: Long): Float {
    var grads = minutes * minuteInGrad
    // kind of nonsense
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}


@Composable
fun ScheduleComponent(
    segments: List<Segment>,
    componentRadius: Int,
    strokeWidth: Float,
    showCurrentTime: Boolean = false,
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


    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(componentRadius.dp)
    ) {
        inset(
            size.width / 2 - componentRadius,
            size.height / 2 - componentRadius
        ) {
            drawCircle(
                color = backColor,
                radius = componentRadius.toFloat(),
                center = center,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            segments.forEach {
                val minutes = getDurationBetween(it.start, it.end).toMinutes()

                drawArc(
                    startAngle = timeToGrad(it.start),
                    sweepAngle = timeToGrad(minutes),
                    useCenter = false,
                    color = frontColor,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
                )
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