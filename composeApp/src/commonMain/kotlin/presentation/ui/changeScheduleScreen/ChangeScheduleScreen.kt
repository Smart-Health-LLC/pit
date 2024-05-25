package presentation.ui.changeScheduleScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Schedule
import domain.model.Segment
import presentation.ui.home.HomeViewModel
import java.time.LocalTime

class ChangeScheduleScreen(
    private val schedule: Schedule,
    private val viewModel: HomeViewModel
) : Screen {
    @Composable
    override fun Content() {

        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(horizontal = 15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { viewModel.updateSchedule() }) {
                    Text(text = "Save")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = { /* TODO */ }) {
                    Text(text = "Warnings")
                }
                Button(onClick = { /* TODO check if the specified interval not conflict with existing ones */ }) {
                    Text(text = "Add")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(25.dp),
            ) {
                item {
                    var index = 0
                    schedule.segments.forEach {
                        index += 1
                        SegmentItem(
                            index,
                            it,
                            viewModel::updateSegmentStartTime,
                            viewModel::updateSegmentEndTime
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                    }
                }
            }
        }
    }

}


@Composable
fun SegmentItem(
    index: Int,
    segment: Segment,
    updateSegmentStart: (index: Int, time: LocalTime) -> Unit,
    updateSegmentEnd: (index: Int, time: LocalTime) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = index.toString())
        DialogTimePickerByButton(true, index, segment.start, updateSegmentStart)
        Canvas(
            Modifier
                .height(1.dp)
                .width(80.dp)
        ) {

            drawLine(
                color = Color.Red,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
        DialogTimePickerByButton(true, index, segment.end, updateSegmentEnd)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Delete, contentDescription = null)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTimePickerByButton(
    is24Hour: Boolean,
    index: Int,
    segmentTimeEdge: LocalTime,
    updateSegmentEdge: (index: Int, time: LocalTime) -> Unit
) {
    // viewmodel information
    var segmentLocalTimeEdge by remember { mutableStateOf(segmentTimeEdge) }

    // control timepicker visibility
    var showDialog by remember { mutableStateOf(false) }

    // time picker state
    val timeState = rememberTimePickerState(
        initialHour = segmentLocalTimeEdge.hour,
        initialMinute = segmentLocalTimeEdge.minute,
        is24Hour = is24Hour
    )

    // timepicker
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(state = timeState)
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = {
                        showDialog = false
                        segmentLocalTimeEdge = LocalTime.of(timeState.hour, timeState.minute)
                        updateSegmentEdge(index, segmentLocalTimeEdge)
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }

    // button that open timepicker
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { showDialog = true }) {
            Text(text = "${timeState.hour} : ${timeState.minute}")
        }
    }
}
