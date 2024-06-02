package presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import kotlin.random.Random


@Composable
fun SmoothLineGraph(modifier: Modifier) {
    Box(
        modifier = modifier.then(
            Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
        )
    ) {
        val animationProgress = remember { Animatable(0f) }

        // animate graph
        LaunchedEffect(key1 = wakeUpLevelsData, block = {
            animationProgress.animateTo(1f, tween(1000))
        })

        val textMeasurer = rememberTextMeasurer()
        val labelTextStyle = MaterialTheme.typography.labelSmall
        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxWidth()
                .align(Alignment.Center)
                .drawWithCache {
                    val path = generateSmoothPath(wakeUpLevelsData, size)
                    val filledPath = Path()
                    filledPath.addPath(path)
                    filledPath.relativeLineTo(0f, size.height)
                    filledPath.lineTo(0f, size.height)
                    filledPath.close()


                    val pathAsleep = generateSmoothPath(fallAsleepLevelsData, size)
                    val filledPathAsleep = Path()
                    filledPathAsleep.addPath(pathAsleep)
                    filledPathAsleep.relativeLineTo(0f, size.height)
                    filledPathAsleep.lineTo(0f, size.height)
                    filledPathAsleep.close()

                    onDrawBehind {
                        val barWidthPx = 1.dp.toPx()
                        //graph border
//                        drawRect(BarColor, style = Stroke(barWidthPx))

                        val horizontalLines = 3
                        val sectionSize = size.height / (horizontalLines + 1)
                        repeat(horizontalLines) { i ->
                            val startY = sectionSize * (i + 1)
                            drawLine(
                                Color.Gray,
                                start = Offset(0f, startY),
                                end = Offset(size.width, startY),
                                strokeWidth = barWidthPx
                            )
                        }

                        // draw data
                        clipRect(right = size.width * animationProgress.value) {
                            // draw line
                            drawPath(path, Color.Green, style = Stroke(2.dp.toPx()))

                            // draw underneath shade
                            drawPath(
                                filledPath,
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Green.copy(alpha = 0.4f),
                                        Color.Transparent
                                    )
                                ),
                                style = Fill
                            )
                        }


                        // draw data
                        clipRect(right = size.width * animationProgress.value) {
                            // draw line
                            drawPath(filledPathAsleep, Color.Magenta, style = Stroke(2.dp.toPx()))

                            // draw underneath shade
                            drawPath(
                                filledPathAsleep,
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Magenta.copy(alpha = 0.4f),
                                        Color.Transparent
                                    )
                                ),
                                style = Fill
                            )
                        }
                    }
                })
    }
}

/**
 * Generate path by provided data and smooth it using cubic bezier
 * @param data Provided data
 */
fun generateSmoothPath(data: List<MetricLevel>, size: Size): Path {
    val path = Path()
    val numberEntries = data.size - 1
    val weekWidth = size.width / numberEntries

    val max = data.maxBy { it.value }
    val min = data.minBy { it.value } // will map to x= 0, y = height
    val range = max.value - min.value
    val heightPxPerAmount = size.height / range

    var previousBalanceX = 0f
    var previousBalanceY = size.height
    data.forEachIndexed { i, balance ->
        if (i == 0) {
            path.moveTo(
                0f,
                size.height - (balance.value - min.value) * heightPxPerAmount
            )
        }

        val balanceX = i * weekWidth
        val balanceY = size.height - (balance.value - min.value) * heightPxPerAmount

        // to do smooth curve graph - we use cubicTo, uncomment section below for non-curve
        val controlPoint1 = Point((balanceX + previousBalanceX) / 2f, previousBalanceY)
        val controlPoint2 = Point((balanceX + previousBalanceX) / 2f, balanceY)
        path.cubicTo(
            controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y,
            balanceX, balanceY
        )

        previousBalanceX = balanceX
        previousBalanceY = balanceY
    }
    return path
}

fun generateRandomEaseLevel(): Float {
    val min = 1f
    val max = 5f
    return Random.nextFloat() * (max - min) + min
}

val wakeUpLevelsData = listOf(
    // @formatter:off
    MetricLevel(LocalDate.now()                         , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(1) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(2) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(3) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(4) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(5) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(6) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(7) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(8) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(9) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(10), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(11), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(12), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(13), generateRandomEaseLevel()),
    // @formatter:on
)

val fallAsleepLevelsData = listOf(
    // @formatter:off
    MetricLevel(LocalDate.now()                         , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(1) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(2) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(3) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(4) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(5) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(6) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(7) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(8) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(9) , generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(10), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(11), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(12), generateRandomEaseLevel()),
    MetricLevel(LocalDate.now().plusWeeks(13), generateRandomEaseLevel()),
    // @formatter:on
)

data class MetricLevel(val date: LocalDate, val value: Float)
data class Point(val x: Float, val y: Float)


@Composable
fun GraphLegendItem(name: String, color: Color, colorSize: Dp) {
    Row {
        FilledCircle(color, colorSize)
        Spacer_4dp()
        Text(text = name, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun FilledCircle(color: Color, sizeCircle: Dp) {
    androidx.compose.foundation.Canvas(modifier = Modifier.size(sizeCircle)) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            style = Fill
        )
    }
}
