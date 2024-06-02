package presentation.ui.adaptation_stats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import com.dokar.sonner.rememberToasterState
import presentation.component.*
import presentation.icon.AchievementIcon
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AdaptationStatsScreenContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        HighProfileBlock()
        Spacer_32dp()


        Spacer_32dp()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            DaySleepOverviewComponent()
        }
        Spacer_16dp()
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
