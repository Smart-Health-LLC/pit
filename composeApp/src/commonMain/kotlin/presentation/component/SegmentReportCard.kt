package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import presentation.icon.SleepSegmentIcon
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun SegmentReportCard(
    idealTimeStart: LocalTime,
    idealTimeEnd: LocalTime,
    wakeUpEaseLevel: Int,
    fallAsleepEaseLevel: Int,
    realTimeStart: LocalTime? = null,
    realTimeEnd: LocalTime? = null,
    noteContent: String? = null,
) {
    ElevatedCard(modifier = Modifier
        .clickable
        {
            // todo stuffchik
        }) {
        Column {
            // main content
            Column(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
                    )
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(18.dp)
            ) {
                Title(idealTimeStart, idealTimeEnd, realTimeStart, realTimeEnd)
                Spacer_4dp()
                SegmentRatesContainer(wakeUpEaseLevel, fallAsleepEaseLevel)
            }

            // note content
            if (noteContent != null) {
                Box(modifier = Modifier.padding(horizontal = 18.dp).padding(vertical = 8.dp)) {
                    Note(noteContent)
                }
            }
        }
    }
}


@Composable
fun Note(noteContent: String) {
    val NOTE_CONTENT_PREVIEW_MAX_LENGTH = 100

    if (noteContent.length > NOTE_CONTENT_PREVIEW_MAX_LENGTH) {
        Text(
            text = noteContent.take(NOTE_CONTENT_PREVIEW_MAX_LENGTH) + "...",
            style = MaterialTheme.typography.bodyMedium
        )
    } else {
        Text(text = noteContent, style = MaterialTheme.typography.bodyMedium)
    }
}

// idk maybe better to just have segment rates component right in the card component
@Composable
fun SegmentRatesContainer(wakeUpEaseLevel: Int, fallAsleepEaseLevel: Int) {
    SegmentRate("Легкость засыпания", wakeUpEaseLevel)
    Spacer(modifier = Modifier.padding(3.dp))
    SegmentRate("Легкость подъема", fallAsleepEaseLevel)
}

const val MAX_SEGMENT_RATE_VALUE = 5

@Composable
fun SegmentRate(rateName: String, value: Int) {
    // Component container
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(rateName, style = MaterialTheme.typography.bodyMedium)
        RateHorizontalIndication(value)
    }
}

@Composable
fun RateHorizontalIndication(value: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        val localValue = generateRandomEaseLevel().toInt()

        LinearProgressIndicator(
            progress = { localValue.toFloat() / MAX_SEGMENT_RATE_VALUE.toFloat() },
            modifier = Modifier.height(10.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.inverseOnSurface,
            strokeCap = StrokeCap.Round,
        )
        Text(text = "$localValue / $MAX_SEGMENT_RATE_VALUE")
    }
}


@Composable
fun Title(
    idealTimeStart: LocalTime,
    idealTimeEnd: LocalTime,
    realTimeStart: LocalTime? = null,
    realTimeEnd: LocalTime? = null,
) {
    // Title container
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {

        // real time indication
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                SleepSegmentIcon,
                modifier = Modifier.size(38.dp),
                contentDescription = null
            )
            Spacer_4dp()
            Text(
                text = (realTimeStart
                    ?: idealTimeStart).format(DateTimeFormatter.ofPattern("HH:mm")) +
                        " - " +
                        (realTimeEnd ?: idealTimeEnd).format(DateTimeFormatter.ofPattern("HH:mm")),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }

        // ideal time suggestion
        SuggestionChip(onClick = { }, label = {
            Text(
                idealTimeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + idealTimeEnd.format(
                    DateTimeFormatter.ofPattern("HH:mm"),
                ),
                style = MaterialTheme.typography.labelMedium
            )
        })
    }
}
