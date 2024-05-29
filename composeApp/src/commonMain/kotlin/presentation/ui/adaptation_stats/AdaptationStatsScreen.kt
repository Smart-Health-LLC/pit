package presentation.ui.adaptation_stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import domain.MAX_SEGMENT_MARK_VALUE
import domain.MIN_SEGMENT_MARK_VALUE
import io.github.koalaplot.core.ChartLayout
import io.github.koalaplot.core.legend.LegendLocation
import io.github.koalaplot.core.line.LinePlot
import io.github.koalaplot.core.style.LineStyle
import io.github.koalaplot.core.util.ExperimentalKoalaPlotApi
import io.github.koalaplot.core.xygraph.*
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.avatar_placeholder
import presentation.icon.AchievementIcon
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class AdaptationStatsScreen : Screen {
    @Composable
    override fun Content() {
        AdaptationStatsScreenContent()
    }
}


@OptIn(ExperimentalKoalaPlotApi::class)
@Composable
fun AdaptationStatsScreenContent(viewModel: AdaptationStatsViewModel = koinInject()) {


    // High profile block
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Short info about schedule
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                "Everyman",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.W500
            )
            Text(
                strings.startedAt + "2023/01/23",
                style = MaterialTheme.typography.labelLarge,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 3.dp)
            )
        }

        // Motivation button
        Box() {
            Icon(AchievementIcon, null, modifier = Modifier.size(49.dp))
        }
    }


    val title = "Общая легкость соблюдения режима"
    val scheduleStartDay = LocalDate.of(2024, 5, 13)
    val yesterday = LocalDate.now().minusDays(1)
    val plotDateFormatter = DateTimeFormatter.ofPattern("dd-MMM");

    val days = ChronoUnit.DAYS.between(scheduleStartDay, yesterday)
    var ii = 1

    val randomListWakeUp =
        List(days.toInt()) { Random.nextFloat() * (MAX_SEGMENT_MARK_VALUE - MIN_SEGMENT_MARK_VALUE) + MIN_SEGMENT_MARK_VALUE }

    var i = 1
    val wakeUpPoints = mutableListOf<Point<Int, Float>>()
    randomListWakeUp.forEach { level ->
        wakeUpPoints.add(Point(i, level))
        i += 1
    }

    val randomListFallAsleep =
        List(days.toInt()) { Random.nextFloat() * (MAX_SEGMENT_MARK_VALUE - MIN_SEGMENT_MARK_VALUE) + MIN_SEGMENT_MARK_VALUE }


    i = 1
    val fallAsleepPoints = mutableListOf<Point<Int, Float>>()
    randomListFallAsleep.forEach { level ->
        fallAsleepPoints.add(Point(i, level))
        i += 1
    }

    // Generate the range of dates and collect them into a list of strings
    val dateList = generateSequence(scheduleStartDay) { date ->
        if (date.isBefore(yesterday)) date.plusDays(1) else null
    }.map { date ->
        date.format(plotDateFormatter)
    }.toList()

    val thumbnail = false
    ChartLayout(
        modifier = paddingMod.then(Modifier.requiredHeight(350.dp)),
        title = { ChartTitle(title) },
        legendLocation = LegendLocation.BOTTOM
    ) {
        XYGraph(
//            xAxisModel = CategoryAxisModel(dateList),
            xAxisModel = IntLinearAxisModel(1..days.toInt()),
            yAxisModel = FloatLinearAxisModel(
                MIN_SEGMENT_MARK_VALUE.toFloat()..MAX_SEGMENT_MARK_VALUE.toFloat(),
                minimumMajorTickSpacing = 50.dp
            ),
            xAxisLabels = {},
            xAxisTitle = {},
            yAxisLabels = {},
            horizontalMinorGridLineStyle = null,
            horizontalMajorGridLineStyle = null,
            verticalMinorGridLineStyle = null
        ) {
//             wake Up Ease Level
            LinePlot(
                data = wakeUpPoints,
                lineStyle = LineStyle(brush = SolidColor(Color(0xFF00498F)), strokeWidth = 2.dp),
//                areaStyle = AreaStyle(
//                    brush = SolidColor(Color(0xFF00498F)),
//                    alpha = 0.5f,
//                ),
//                areaBaseline = AreaBaseline.ConstantLine(0f)
            )
            // fall Asleep Ease Level
            LinePlot(
                data = fallAsleepPoints,
                lineStyle = LineStyle(brush = SolidColor(Color(0xFF37A78F)), strokeWidth = 2.dp),
//                areaStyle = AreaStyle(
//                    brush = SolidColor(Color(0xFF37A78F)),
//                    alpha = 0.5f,
//                ),
//                areaBaseline = AreaBaseline.ConstantLine(0f)
            )
        }
    }


}

internal val padding = 8.dp
internal val paddingMod = Modifier.padding(padding)

@Composable
fun ChartTitle(title: String) {
    Column {
        Text(
            title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun AxisLabel(label: String, modifier: Modifier = Modifier) {
    Text(
        label,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun AxisTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        title,
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}
