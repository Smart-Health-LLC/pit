package presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.vectorResource
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.ic_dots_vertical
import presentation.icon.WatchLater
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
    Card(modifier = Modifier
        .clickable
        {
            // todo stuffchik
        }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Title(idealTimeStart, idealTimeEnd, realTimeStart, realTimeEnd)

            Spacer(modifier = Modifier.padding(8.dp))

            SegmentRatesContainer(wakeUpEaseLevel, fallAsleepEaseLevel)
            if (noteContent != null) {
                Spacer(modifier = Modifier.padding(8.dp))
                VerticalDivider()
                Spacer(modifier = Modifier.padding(8.dp))

                Note(noteContent)
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(rateName, style = MaterialTheme.typography.bodyLarge)
        RateHorizontalIndication(value)
    }
}

@Composable
fun RateHorizontalIndication(value: Int) {
    LinearProgressIndicator(
        modifier = Modifier
            .requiredWidth(140.dp)
            .height(15.dp)
            .clip(RoundedCornerShape(14.dp)),
        trackColor = Color.Cyan,
//        progress = value.toFloat() / MAX_SEGMENT_RATE_VALUE.toFloat() * 100
        progress = 0.6f
    )
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

        // times indication
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Outlined.WatchLater,
                modifier = Modifier.size(38.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column {
                val idealTextColor = if (realTimeStart != null || realTimeEnd != null) {
                    Color(0x57000000)
                } else {
                    Color.Black
                }
                Text(
                    text = idealTimeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + idealTimeEnd.format(
                        DateTimeFormatter.ofPattern("HH:mm")
                    ),
                    color = idealTextColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if (realTimeStart != null || realTimeEnd != null) {
                    val timeStart = realTimeStart ?: idealTimeStart
                    val timeEnd = realTimeEnd ?: idealTimeEnd
                    Text(
                        timeStart.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + timeEnd.format(
                            DateTimeFormatter.ofPattern("HH:mm"),
                        ),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }


        // button indication
        Icon(
            vectorResource(Res.drawable.ic_dots_vertical),
            contentDescription = null
        )

    }

}
