package presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import presentation.theme.Inter
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTimePickerByButton(
    is24Hour: Boolean,
    index: Int,
    segmentTimeEdge: LocalTime,
    updateSegmentEdge: (index: Int, time: LocalTime) -> Unit,
) {
    // viewmodel information
    var segmentLocalTimeEdge by remember { mutableStateOf(segmentTimeEdge) }

    // control timepicker visibility
    var showDialog by remember { mutableStateOf(false) }

    // timepicker state
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
            Surface(
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePicker(state = timeState)

                    // buttons row
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showDialog = false }) {
                            Text(text = strings.dismiss)
                        }
                        TextButton(onClick = {
                            showDialog = false
                            segmentLocalTimeEdge = LocalTime.of(timeState.hour, timeState.minute)
                            updateSegmentEdge(index, segmentLocalTimeEdge)
                        }) {
                            Text(text = strings.confirm)
                        }
                    }
                }
            }
        }
    }

    Surface(onClick = { showDialog = true }) {
        Text(
            text = "${timeState.hour}:${timeState.minute}",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter()
        )
    }
}
