package presentation.ui.rate

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Segment
import org.koin.compose.koinInject
import presentation.extention.supportWideScreen
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

    val state = viewModel.state.collectAsState().value


    QuestionsLayout(
        onDonePressed = viewModel::onSavePressed,
        shouldShowDoneButton = state.isFormComplete,
        segment = viewModel.thisSegmentInfo
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {
            CheckboxQuestion(
                text = "Skipped?",
                selected = state.isSegmentSkipped,
                onOptionSelected = viewModel::toggleSkippedStatus
            )

            if (!state.isSegmentSkipped) {
                CheckboxQuestion(
                    text = "Реальное время периода отличается от того, что в распиании",
                    selected = state.isSegmentDiffersFromScheduled,
                    onOptionSelected = viewModel::toggleDifferFromScheduleStatus
                )

                if (state.isSegmentDiffersFromScheduled) {
                    TimeEdgesQuestion("New time edges")
                }

                // 2 sliders
                SliderQuestion(
                    questionTitleText = "Оцените легкость подъема",
                    value = state.wakeApEaseLevel.toFloat(),
                    onValueChange = viewModel::updateWakeUpEaseLevel,
                    startTextResource = "Сложно",
                    neutralTextResource = "Не знаю",
                    endTextResource = "Легко"
                )


                SliderQuestion(
                    questionTitleText = "Оцените легкость засыпания",
                    value = state.fallAsleepEaseLevel.toFloat(),
                    onValueChange = viewModel::updateFallAsleepEaseLevel,
                    startTextResource = "Сложно",
                    neutralTextResource = "Не знаю",
                    endTextResource = "Легко"
                )
            }
        }
    }
}

@Composable
fun QuestionsLayout(
    onDonePressed: () -> Unit,
    shouldShowDoneButton: Boolean,
    segment: Segment,
    content: @Composable (PaddingValues) -> Unit,
) {
    Surface(modifier = Modifier.supportWideScreen()) {
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
    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                Column {
                    segment.day?.let {
                        Text(
                            text = it.format(DateTimeFormatter.ofPattern(dateFormat)),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeEmphasizedAlpha)
                        )
                    }
                    Text(
                        text = "${segment.start.format(dateTimeFormatter)} - ${
                            segment.end.format(
                                dateTimeFormatter
                            )
                        }",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
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
