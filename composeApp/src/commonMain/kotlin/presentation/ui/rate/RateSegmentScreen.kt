package presentation.ui.rate

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dokar.sonner.rememberToasterState
import org.koin.compose.koinInject
import presentation.component.DatePickerWithDialog
import presentation.component.ToasterWrapper
import presentation.ui.rate.question_type.SliderQuestion
import presentation.ui.rate.question_type.TimeEdgesQuestion


class RateSegmentScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: RateSegmentViewModel = koinInject()

        val localNavigator = LocalNavigator.currentOrThrow

        val state by viewModel.state.collectAsState()

        val localBottomSheetNavigation = LocalBottomSheetNavigator.current
        val toasterState = rememberToasterState { }
        ToasterWrapper(toasterState)

        Surface {
            Column(
                modifier = Modifier.clip(MaterialTheme.shapes.large)
                    .padding(bottom = 35.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // Date time picker
                    DatePickerWithDialog(modifier = Modifier.align(Alignment.CenterStart))
                    BottomSheetDefaults.DragHandle(
                        modifier = Modifier.align(Alignment.Center)
                    )
                    // Save button
                    TextButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = {
                            viewModel.onSavePressed()
                            localBottomSheetNavigation.hide()
                        },
                        enabled = state.isFormComplete,
                    ) {
                        Text(text = strings.save, fontWeight = FontWeight.Bold)
                    }
                }
                // Input for segment time
                TimeEdgesQuestion(
                    start = state.start,
                    end = state.end,
                    updateSegmentStart = viewModel::updateStartTime,
                    updateSegmentEnd = viewModel::updateEndTime
                )
                // wake level up slider
                SliderQuestion(
                    questionTitleText = strings.wakeUpEase,
                    value = state.wakeApEaseLevel.toFloat(),
                    onValueChange = viewModel::updateWakeUpEaseLevel,
                    startTextResource = strings.easeLow,
                    neutralTextResource = strings.easeMiddle,
                    endTextResource = strings.easeHigh
                )
                // fall asleep slider
                SliderQuestion(
                    questionTitleText = strings.fallAsleepEase,
                    value = state.fallAsleepEaseLevel.toFloat(),
                    onValueChange = viewModel::updateFallAsleepEaseLevel,
                    startTextResource = strings.easeLow,
                    neutralTextResource = strings.easeMiddle,
                    endTextResource = strings.easeHigh
                )
                // note text area
                Textarea(
                    modifier = Modifier.height(120.dp),
                    onChange = viewModel::updateNote,
                    initialText = state.note ?: ""
                )
            }
        }
    }
}


@Composable
fun Textarea(
    modifier: Modifier = Modifier,
    onChange: (newText: String) -> Unit,
    initialText: String = ""
) {
    var localText by rememberSaveable { mutableStateOf(initialText) }
    OutlinedTextField(
        value = localText,
        onValueChange = {
            onChange(it)
            localText = it
        },
        label = {
            Text(
                text = strings.note,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = false
    )
}