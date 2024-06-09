package presentation.ui.change_schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.lyricist.strings
import domain.model.Segment
import presentation.component.*
import java.time.LocalTime

@Composable
fun CreateSegmentDialog(
    modifier: Modifier = Modifier,
    title: String,
    onDismiss: () -> Unit,
    onCreate: (segment: Segment) -> Unit
) {
    var newSegment by remember {
        mutableStateOf(
            Segment(
                start = LocalTime.of(0, 0),
                end = LocalTime.of(0, 0)
            )
        )
    }

    BasicAlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // title
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                SegmentTimeEdges(segment = newSegment,
                    updateSegmentEnd = {
                        newSegment = newSegment.copy(
                            end = it
                        )
                    },
                    updateSegmentStart = {
                        newSegment = newSegment.copy(
                            start = it
                        )
                    }
                )

                Spacer_24dp()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onDismiss,
                    ) {
                        Text(strings.dialogStrings.dismiss)
                    }
                    TextButton(
                        onClick = {
                            onCreate(newSegment)
                        }
                    ) {
                        Text(strings.dialogStrings.ok)
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorMessagesDialog(
    modifier: Modifier = Modifier,
    title: String,
    onDismiss: () -> Unit,
    messages: List<String>,
) {

    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = modifier
                .wrapContentWidth(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // title
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    messages.forEach {
                        item {
                            Text(
                                modifier = Modifier.fillMaxWidth()
                                    .background(
                                        color = MaterialTheme.colorScheme.errorContainer,
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .padding(vertical = 8.dp, horizontal = 8.dp),
                                text = it,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    textAlign = TextAlign.Center,
                                ),
                            )
                            Spacer_8dp()
                        }
                    }
                }
                Spacer_12dp()
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(strings.dialogStrings.ok)
                }
            }
        }
    }
}
