package presentation.ui.change_schedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.dokar.sonner.rememberToasterState
import domain.model.Segment
import org.koin.compose.koinInject
import presentation.component.DialogTimePickerByButton
import presentation.component.ToasterWrapper
import presentation.icon.ArrowRightIcon
import java.time.LocalTime

class ChangeScheduleScreen : Screen {
    @Composable
    override fun Content() {
        ChangeScheduleScreenContent()
    }
}


@Composable
fun ChangeScheduleScreenContent(viewModel: ChangeScheduleViewModel = koinInject()) {

    // todo fix notification
    val state by viewModel.screenState.collectAsState()


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
//            item {
//                var index = 0
//                schedule.segments.forEach {
//                    index += 1
//                    SegmentItem(
//                        index,
//                        it,
//                        viewModel::updateSegmentStartTime,
//                        viewModel::updateSegmentEndTime,
//                    )
//                    Spacer(modifier = Modifier.padding(12.dp))
//                }
//            }
        }
    }
}


@Composable
fun SegmentItem(
    segment: Segment,
    updateSegmentStart: (time: LocalTime) -> Unit,
    updateSegmentEnd: (time: LocalTime) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
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


