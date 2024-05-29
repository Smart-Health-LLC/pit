package presentation.ui.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import i18n.LocaleInfo


@Composable
fun SingleChoiceAlertDialog(
    selectedItemKey: LocaleInfo,
    onItemSelected: (String) -> Unit,
    items: List<LocaleInfo>
) {
    val userSelectedItem = remember { mutableStateOf(selectedItemKey) }

    AlertDialog(
        onDismissRequest = { onItemSelected(selectedItemKey) },
        title = { Text(text = "Single choice") },
        text = {
            Column {
                items.forEach { sampleItem ->
                    val isSelected = sampleItem.key == userSelectedItem.value
                    LabelRadioButton(
                        item = sampleItem,
                        isSelected = isSelected,
                        onClick = { userSelectedItem.value = sampleItem.key },
                    )
                }
            }
        },
        confirmButton = if (userSelectedItem.value == null) {
            {
                TextButton(
                    onClick = { onItemSelected(null) },
                ) {
                    Text(text = "Cancel")
                }
            }
        } else {
            {
                TextButton(
                    onClick = { onItemSelected(userSelectedItem.value) },
                ) {
                    Text(text = "Select")
                }
            }
        },
        dismissButton = if (userSelectedItem.value == null) {
            null
        } else {
            {
                TextButton(
                    onClick = { onItemSelected(null) },
                ) {
                    Text(text = "Clear")
                }
            }
        },
    )
}


@Composable
fun LabelRadioButton(
    item: LocaleInfo,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier.clickable(
            role = Role.RadioButton,
            onClick = onClick,
            onClickLabel = item.title,
        ),
        headlineContent = { Text(text = item.name) },
        trailingContent = { RadioButton(selected = isSelected, onClick = null) },
    )
}


object SampleData {
    val items = listOf(
        SampleItem(
            key = "item-0",
            title = "Item #0",
            description = "Subtitle of item #0",
        ),
        SampleItem(
            key = "item-1",
            title = "Item #1",
            description = "Subtitle of item #1",
        ),
        SampleItem(
            key = "item-2",
            title = "Item #2",
            description = "Subtitle of item #2",
        )
    )
}

data class SampleItem(
    val key: String,
    val title: String,
    val description: String,
)