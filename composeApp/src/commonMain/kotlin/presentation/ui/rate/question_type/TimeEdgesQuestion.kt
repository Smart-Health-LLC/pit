package presentation.ui.rate.question_type

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.change_schedule.SegmentItem
import java.time.LocalTime

@Composable
fun TimeEdgesQuestion(
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.small,

        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 4.dp),
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