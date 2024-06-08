package presentation.ui.change_schedule

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import domain.model.Segment
import org.koin.compose.koinInject
import presentation.component.*
import presentation.icon.SaveIcon

class ChangeScheduleScreen : Screen {
    @Composable
    override fun Content() {
        ChangeScheduleScreenContent()
    }
}

@Composable
fun ChangeScheduleScreenContent(viewModel: ChangeScheduleViewModel = koinInject()) {
    val state by viewModel.screenState.collectAsState()
    // todo maybe add error toaster

    Scaffold(
        topBar = {},
        bottomBar = {},
        floatingActionButton = {
            // floating action block
            ControlPanel(
                errorCodes = state.errors,
                onCreateSegment = viewModel::addSegment,
                onSave = viewModel::updateSchedule,
                newScheduleState = state.newScheduleState
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .padding(top = 15.dp)
                .padding(paddingValues = it)
        ) {
            ScheduleComponent(
                segments = state.editableSegments,
                componentRadius = 320,
                strokeWidth = 190f,
                useRandomColors = true,
                onAddSegment = { segment ->
                    viewModel.addSegment(segment)
                },
                onUpdateSegment = { segment, newStart, newEnd ->
                    viewModel.updateSegmentStartTimeAndEndTime(segment, newStart, newEnd)
                }
            )
            Spacer_12dp()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // time edges components
                state.editableSegments.forEach { segment ->
                    item {
                        SegmentTimeEdges(
                            segment = segment,
                            updateSegmentEnd = { newTime ->
                                viewModel.updateSegmentEndTime(segment, newTime)
                            },
                            updateSegmentStart = { newTime ->
                                viewModel.updateSegmentStartTime(segment, newTime)
                            },
                            onDelete = {
                                viewModel.deleteSegment(segment)
                            }
                        )
                    }
                }

                // padding to be able to scroll in the bottom and see last item even with floating action block
                item {
                    Spacer(modifier = Modifier.padding(40.dp))
                }
            }
        }
    }
}

@Composable
fun ControlPanel(
    errorCodes: List<ErrorCode>,
    onCreateSegment: (newSegment: Segment) -> Unit,
    onSave: () -> Unit,
    newScheduleState: NewScheduleState
) {
    var isErrorsDialogOpen by remember { mutableStateOf(false) }
    var isAddNewDialogOpen by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
//            .width(320.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .animateContentSize()
            .padding(horizontal = 16.dp, vertical = 10.dp) // standard FAB m3 padding
    ) {
        ControlItem(
            onClick = {
                isAddNewDialogOpen = true
            },
            icon = Icons.Outlined.Add,
            label = strings.add
        )

        if (errorCodes.isEmpty() && newScheduleState == NewScheduleState.EDITED) {
            ControlItem(
                onClick = onSave,
                icon = SaveIcon,
                strings.save
            )
        }
        if (errorCodes.isNotEmpty()) {
            ControlItem(
                onClick = {
                    isErrorsDialogOpen = true
                },
                icon = Icons.Outlined.Warning,
                label = strings.errors,
                contentColor = MaterialTheme.colorScheme.error
            )
        }
    }

    if (isErrorsDialogOpen) {
        ErrorMessagesDialog(
            title = strings.rulesBroken,
            messages = errorCodes.map { strings.errorDescriptionByCode(it) },
            onDismiss = { isErrorsDialogOpen = false }
        )
    }

    if (isAddNewDialogOpen) {
        CreateSegmentDialog(
            title = strings.defineSegment,
            onCreate = {
                onCreateSegment(it)
                isAddNewDialogOpen = false
            },
            onDismiss = {
                isAddNewDialogOpen = false
            }
        )
    }
}

@Composable
fun ControlItem(
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer
) {
    IconButton(
        modifier = Modifier.background(Color.Transparent),
        onClick = onClick,
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = contentColor
                )
//                Text(text = label, color = contentColor)
            }
        }
    )
}
