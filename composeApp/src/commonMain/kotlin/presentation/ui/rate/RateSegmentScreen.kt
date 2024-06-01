package presentation.ui.rate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.model.Segment
import org.koin.compose.koinInject
import presentation.component.Spacer_4dp
import presentation.extention.supportWideScreen
import presentation.theme.Inter
import presentation.theme.stronglyDeEmphasizedAlpha
import presentation.ui.rate.question_type.SliderQuestion
import presentation.ui.rate.question_type.TimeEdgesQuestion
import presentation.wtf.dateFormat
import presentation.wtf.dateTimeFormatter
import java.time.format.DateTimeFormatter


class RateSegmentScreen : Screen {
    @Composable
    override fun Content() {

        Column(
            modifier = Modifier.clip(MaterialTheme.shapes.large)
                .padding(bottom = 35.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BottomSheetDefaults.DragHandle(
                    modifier = Modifier.align(Alignment.Center)
                )
                TextButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {},
                    enabled = true
                ) {
                    Text(text = strings.save, fontWeight = FontWeight.Bold)
                }
            }
            TimeEdgesQuestion(
            )
            // 2 sliders
            SliderQuestion(
                questionTitleText = strings.wakeUpEase,
                value = 4f,
                onValueChange = {},
                startTextResource = strings.easeLow,
                neutralTextResource = strings.easeMiddle,
                endTextResource = strings.easeHigh
            )
            SliderQuestion(
                questionTitleText = strings.fallAsleepEase,
                value = 3f,
                onValueChange = {},
                startTextResource = strings.easeLow,
                neutralTextResource = strings.easeMiddle,
                endTextResource = strings.easeHigh
            )
            Textarea(modifier = Modifier.height(120.dp))
        }
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
                TimeEdgesQuestion()
            }

            item {
                // 2 sliders
                SliderQuestion(
                    questionTitleText = strings.wakeUpEase,
                    value = state.wakeApEaseLevel.toFloat(),
                    onValueChange = viewModel::updateWakeUpEaseLevel,
                    startTextResource = strings.easeLow,
                    neutralTextResource = strings.easeMiddle,
                    endTextResource = strings.easeHigh
                )
            }

            item {
                SliderQuestion(
                    questionTitleText = strings.fallAsleepEase,
                    value = state.fallAsleepEaseLevel.toFloat(),
                    onValueChange = viewModel::updateFallAsleepEaseLevel,
                    startTextResource = strings.easeLow,
                    neutralTextResource = strings.easeMiddle,
                    endTextResource = strings.easeHigh
                )
            }

            item {
                Textarea()
            }
        }
    }
}


@Composable
fun Textarea(modifier: Modifier = Modifier) {
    var text by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
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
