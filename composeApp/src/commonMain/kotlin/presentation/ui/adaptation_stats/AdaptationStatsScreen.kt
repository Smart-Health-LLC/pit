package presentation.ui.adaptation_stats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import com.dokar.sonner.rememberToasterState
import org.koin.compose.koinInject
import presentation.component.*
import presentation.icon.AchievementIcon
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AdaptationStatsScreenContent(viewModel: AdaptationStatsViewModel = koinInject()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HighProfileBlock()
        Spacer_32dp()
        // Graph element
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primaryContainer),
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(vertical = 20.dp)
            ) {

                // graph description
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = strings.graphEase,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                SmoothLineGraph(Modifier.height(240.dp))
                Spacer_4dp()
                // Custom graph legend
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GraphLegendItem(strings.wakeUpEase, Color.Green, 15.dp)
                    Spacer_16dp()
                    GraphLegendItem(strings.fallAsleepEase, Color.Magenta, 15.dp)
                }
            }
        }

        Spacer_32dp()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            DaySleepOverviewComponent()
        }
        Spacer_16dp()
    }

}


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
    Canvas(modifier = Modifier.size(sizeCircle)) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            style = Fill
        )
    }
}

@Composable
fun HighProfileBlock() {
    // High profile block
    Row(
        modifier = Modifier
            .fillMaxWidth(),
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
                strings.startedAt + LocalDate.now().minusMonths(1)
                    .format(DateTimeFormatter.ISO_LOCAL_DATE),
                style = MaterialTheme.typography.labelLarge,
                color = Color.LightGray,
                modifier = Modifier.padding(start = 3.dp)
            )
        }

        val toasterState = rememberToasterState { }
        ToasterWrapper(toasterState)
        val toasterMessage = strings.inDevelopment
        // Motivation button
        IconButton(
            onClick = {
                toasterState.show(toasterMessage)
            },
            enabled = true
        ) {
            Icon(AchievementIcon, null, modifier = Modifier.size(25.dp))
        }
    }

}
