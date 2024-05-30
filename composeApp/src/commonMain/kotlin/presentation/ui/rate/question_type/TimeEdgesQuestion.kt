package presentation.ui.rate.question_type

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import presentation.ui.change_schedule.SegmentItem
import java.time.LocalTime

@Composable
fun TimeEdgesQuestion(
    text: String,
    selected: Boolean,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,

        border = BorderStroke(
            width = 1.dp,
            color = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().animateContentSize()
        ) {
            // checkbox section
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clickable(onClick = onOptionSelected)
                    .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                    .background(
                        color = if (selected) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    ).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text, Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                Box(Modifier.padding(8.dp)) {
                    Checkbox(selected, onCheckedChange = null)
                }
            }

            // custom time section
            if (selected) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(horizontal = 4.dp).clickable {},
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SegmentItem(
                        1,
                        segment = domain.model.Segment(
                            LocalTime.of(12, 43),
                            LocalTime.of(14, 15),
                        ),
                        { roa, ht -> }, { roah, aht -> }
                    )
                }
            }
        }
    }
}