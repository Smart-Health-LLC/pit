package presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import domain.model.Segment
import presentation.icon.ArrowRightIcon
import java.time.LocalTime

@Composable
fun SegmentTimeEdges(
    modifier: Modifier = Modifier,
    segment: Segment,
    updateSegmentStart: (time: LocalTime) -> Unit,
    updateSegmentEnd: (time: LocalTime) -> Unit,
    onDelete: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .pointerInput(onDelete) {
                detectTapGestures(onLongPress = {
                    if (onDelete != null) {
                        onDelete()
                    }
                })
            }
            .fillMaxWidth(),
//            .combinedClickable(
//                onClick = { },
//                onLongClick = onDelete,
//            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DialogTimePickerByButton(true, segment.start, updateSegmentStart)
        Icon(
            imageVector = ArrowRightIcon,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        DialogTimePickerByButton(true, segment.end, updateSegmentEnd)
    }
}
