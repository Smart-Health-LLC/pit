package presentation.component

import androidx.compose.foundation.Canvas
import java.time.Duration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp
import domain.model.Schedule
import java.time.LocalTime

val CIRCULAR_TIMER_RADIUS = 150
val STROKE_WIDTH = 70f
val minuteInGrad = 0.25
val startPosInGrads = 270

fun timeToGrad(time: LocalTime): Float {
    val minutes = time.hour * 60 + time.minute
    var grads = startPosInGrads + minutes * minuteInGrad
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}


fun timeToGrad(minutes: Long): Float {
    var grads = minutes * minuteInGrad
    // kind of nonsense
    if (grads >= 360)
        grads -= 360
    return grads.toFloat()
}


@Composable
fun ScheduleComponent(schedule: Schedule) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(CIRCULAR_TIMER_RADIUS.dp)
    ) {
        inset(
            size.width / 2 - CIRCULAR_TIMER_RADIUS,
            size.height / 2 - CIRCULAR_TIMER_RADIUS
        ) {
            drawCircle(
                color = Color.Gray,
                radius = CIRCULAR_TIMER_RADIUS.toFloat(),
                center = center,
                style = Stroke(width = STROKE_WIDTH, cap = StrokeCap.Round)
            )

            schedule.segments.forEach {
                val minutes: Long
                if (!it.start.isBefore(it.end)) {
                    minutes =
                        Duration.between(it.start, LocalTime.MAX).toMinutes() + Duration.between(
                            LocalTime.MIN,
                            it.end
                        ).toMinutes() + 1
                } else {
                    val duration = Duration.between(it.start, it.end)
                    minutes = duration.toMinutes()
                }
                drawArc(
                    startAngle = timeToGrad(it.start),
                    sweepAngle = timeToGrad(minutes),
                    useCenter = false,
                    color = Color.Red,
                    style = Stroke(width = STROKE_WIDTH, cap = StrokeCap.Butt)
                )
            }
        }
    }
}
