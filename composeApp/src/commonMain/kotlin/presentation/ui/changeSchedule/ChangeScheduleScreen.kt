package presentation.ui.changeSchedule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import domain.model.Schedule
import domain.model.Segment
import presentation.component.DialogTimePickerByButton
import presentation.component.ToasterWrapper
import presentation.ui.home.HomeViewModel
import java.time.LocalTime

class ChangeScheduleScreen(
    private val schedule: Schedule,
    private val viewModel: HomeViewModel
) : Screen {
    // todo fix notification
    @Composable
    override fun Content() {

        val toasterState = rememberToasterState(onToastDismissed = {
//            viewModel.updateToastState(false)
        })
        ToasterWrapper(toasterState)
        val showToaster = viewModel.toastState.collectAsState().value
//        toasterState.li
        if (showToaster) {
            toasterState.show("shit happends")
        }


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
                            viewModel::updateSegmentEndTime,
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
    updateSegmentEnd: (index: Int, time: LocalTime) -> Unit,
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


