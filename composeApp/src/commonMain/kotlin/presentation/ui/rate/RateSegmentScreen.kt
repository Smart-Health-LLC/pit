package presentation.ui.rate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Segment
import org.koin.compose.koinInject
import presentation.component.Spacer_4dp
import presentation.extention.supportWideScreen
import presentation.theme.Inter
import presentation.theme.stronglyDeEmphasizedAlpha
import presentation.ui.rate.question_type.*
import presentation.wtf.dateFormat
import presentation.wtf.dateTimeFormatter
import java.time.format.DateTimeFormatter


class RateSegmentScreen : Screen {
    @Composable
    override fun Content() {
        RateSegmentScreenContent()
    }
}


@Composable
fun RateSegmentScreenContent(
    viewModel: RateSegmentViewModel = koinInject()
) {

    val localNavigator = LocalNavigator.currentOrThrow

    val state = viewModel.state.collectAsState().value

    QuestionsLayout(
        onDonePressed = {
            viewModel.onSavePressed()
            localNavigator.pop()
        },
        shouldShowDoneButton = state.isFormComplete,
        segment = viewModel.thisSegmentInfo
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                CheckboxQuestion(
                    text = "Skipped?",
                    selected = state.isSegmentSkipped,
                    onOptionSelected = viewModel::toggleSkippedStatus
                )
            }

            if (!state.isSegmentSkipped) {
                item {
                    TimeEdgesQuestion(
                        "New time edges",
                        selected = state.isSegmentDiffersFromScheduled,
                        onOptionSelected = viewModel::toggleDifferFromScheduleStatus
                    )
                }

                item {
                    // 2 sliders
                    SliderQuestion(
                        questionTitleText = "Оцените легкость подъема",
                        value = state.wakeApEaseLevel.toFloat(),
                        onValueChange = viewModel::updateWakeUpEaseLevel,
                        startTextResource = "Сложно",
                        neutralTextResource = "Пойдёт",
                        endTextResource = "Легко"
                    )
                }

                item {
                    SliderQuestion(
                        questionTitleText = "Оцените легкость засыпания",
                        value = state.fallAsleepEaseLevel.toFloat(),
                        onValueChange = viewModel::updateFallAsleepEaseLevel,
                        startTextResource = "Сложно",
                        neutralTextResource = "Не знаю",
                        endTextResource = "Легко"
                    )
                }

                item {
                    Textarea()
                }
            }
        }
    }
}


@Composable
fun Textarea() {
    val text = rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        label = {
            Text(
                text = "Заметочка",
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true
    )

}

@Composable
fun QuestionsLayout(
    onDonePressed: () -> Unit,
    shouldShowDoneButton: Boolean,
    segment: Segment,
    content: @Composable (PaddingValues) -> Unit,
) {
    Surface(
        modifier = Modifier.supportWideScreen()
    ) {
        Scaffold(
            topBar = {
                SurveyTopAppBar(segment)
            },
            content = content,
            bottomBar = {
                SurveyBottomBar(
                    shouldShowDoneButton = shouldShowDoneButton,
                    onDonePressed = onDonePressed
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class) // CenterAlignedTopAppBar is experimental in m3
@Composable
fun SurveyTopAppBar(segment: Segment) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(bottom = 10.dp)) {
        CenterAlignedTopAppBar(
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "${segment.start.format(dateTimeFormatter)} - ${
                            segment.end.format(
                                dateTimeFormatter
                            )
                        }",
                        fontFamily = Inter(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    segment.day?.let {
                        Spacer_4dp()
                        Text(
                            text = it.format(DateTimeFormatter.ofPattern(dateFormat)),
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeEmphasizedAlpha)
                        )
                    }
                }
            },
        )
    }
}


@Composable
fun SurveyBottomBar(
    shouldShowDoneButton: Boolean,
    onDonePressed: () -> Unit
) {
    Surface(shadowElevation = 7.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // Since we're not using a Material component but we implement our own bottom bar,
                // we will also need to implement our own edge-to-edge support. Similar to the
                // NavigationBar, we add the horizontal and bottom padding if it hasn't been consumed yet.
                .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom))
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                onClick = onDonePressed,
                enabled = shouldShowDoneButton
            ) {
                Text(text = "Save")
            }
        }
    }
}
