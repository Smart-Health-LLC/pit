package presentation.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.lyricist.strings
import io.github.aakira.napier.Napier
import presentation.wtf.CUSTOM_TAG
import java.time.*
import java.time.format.DateTimeFormatter


@Composable
fun DatePickerWithDialog(
    modifier: Modifier = Modifier,
    onDayUpdate: (newDate: LocalDate) -> Unit,
    initialData: LocalDate = LocalDate.now()
) {
    val datePickerState = rememberDatePickerState()
    val selectedLocalDate = datePickerState.selectedDateMillis?.let {
        convertMillisToLocalDate(it)
    }
    val selectedDateInString = selectedLocalDate?.let {
        selectedLocalDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
    } ?: initialData.format(DateTimeFormatter.ISO_LOCAL_DATE)

    var showDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = {
            showDialog = true
        },
        enabled = true
    ) {
        Text(text = selectedDateInString, fontWeight = FontWeight.Bold)
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        if (datePickerState.selectedDateMillis != null) {
                            onDayUpdate(convertMillisToLocalDate(datePickerState.selectedDateMillis!!))
                        } else {
                            Napier.d(tag = CUSTOM_TAG) { "[DatePickerWithDialog] nonsense! dateState.selectedDateMillis == null" }
                        }
                    }
                ) {
                    Text(text = strings.ok)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(text = strings.cancel)
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = true
            )
        }
    }
}


fun convertMillisToLocalDate(millis: Long): LocalDate {
    return Instant
        .ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}