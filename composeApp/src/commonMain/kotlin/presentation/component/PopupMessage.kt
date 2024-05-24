package presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun PopupMessage(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        icon = {
            Icon(Icons.Outlined.Info, contentDescription = null)
        },
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = onConfirm,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center,
                ),
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    textAlign = TextAlign.Center,
                ),
            )
        },
        dismissButton = {},
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onConfirm,
            ) {
                Text(
                    text = "OK",
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        },
    )
}

