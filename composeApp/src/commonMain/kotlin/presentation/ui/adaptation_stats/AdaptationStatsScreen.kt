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
import org.koin.compose.koinInject
import presentation.component.Spacer_16dp
import presentation.component.Spacer_4dp
import presentation.icon.AchievementIcon


@Composable
fun AdaptationStatsScreenContent(viewModel: AdaptationStatsViewModel = koinInject()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HighProfileBlock()
        Spacer_16dp()
        // Graph element
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .background(PurpleBackgroundColor),
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
                        text = "Легкость",
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
                    GraphLegendItem("Легкость подъема", Color.Green, 15.dp)
                    Spacer_16dp()
                    GraphLegendItem("Легкость засыпания", Color.Blue, 15.dp)
                }
            }
        }

        Spacer_16dp()
        OverViewBlock()
    }

}

@Composable
fun OverViewBlock() {
    // mah
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
            Icon(AchievementIcon, null, modifier = Modifier.size(25.dp))
        }
    }

}
