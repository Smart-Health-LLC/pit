package presentation.ui.settings.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import cafe.adriel.lyricist.strings
import i18n.LocaleInfo


@Composable
fun SingleChoiceAlertDialog(
    previouslySelectedLanguage: LocaleInfo, // language that was selected before dialog was opened
    onItemSelected: (LocaleInfo) -> Unit,
    onDialogDismiss: () -> Unit,
    items: List<LocaleInfo>
) {
    // local user selection state used to show dismiss\ok\cancel button
    val userSelectedItem = remember { mutableStateOf(previouslySelectedLanguage) }

    AlertDialog(
        onDismissRequest = onDialogDismiss,
        title = { Text(text = strings.selectNewLanguage) },
        text = {
            Column {
                items.forEach { localeItem ->
                    val isSelected = localeItem == userSelectedItem.value
                    LabelRadioButton(
                        item = localeItem,
                        isSelected = isSelected,
                        onClick = { userSelectedItem.value = localeItem },
                    )
                }
            }
        },
        // close the dialog before select any language
        confirmButton = if (userSelectedItem.value == previouslySelectedLanguage) {
            {
                TextButton(
                    onClick = onDialogDismiss,
                ) {
                    Text(text = strings.cancel)
                }
            }
        }
        // confirm new selected language
        else {
            {
                TextButton(
                    onClick = { onItemSelected(userSelectedItem.value) },
                ) {
                    Text(text = strings.confirm)
                }
            }
        },
        // close the dialog even after select any language
        dismissButton = if (userSelectedItem.value == previouslySelectedLanguage) {
            null
        } else {
            {
                TextButton(
                    onClick = onDialogDismiss,
                ) {
                    Text(text = strings.clearChoice)
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
//            onClickLabel = item.title,
        ),
        headlineContent = { Text(text = item.name) },
        trailingContent = { RadioButton(selected = isSelected, onClick = null) },
    )
}