package presentation.ui.rate.question_type

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.SegmentTimeEdges
import java.time.LocalTime

@Composable
fun TimeEdgesQuestion(
    modifier: Modifier = Modifier,
    start: LocalTime,
    end: LocalTime,
    updateSegmentStart: (time: LocalTime) -> Unit,
    updateSegmentEnd: (time: LocalTime) -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.inverseOnSurface,
        ),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SegmentTimeEdges(
                segment = domain.model.Segment(start, end),
                updateSegmentStart = updateSegmentStart,
                updateSegmentEnd = updateSegmentEnd
            )
        }
    }
}