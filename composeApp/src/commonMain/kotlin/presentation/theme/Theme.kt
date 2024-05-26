package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

const val stronglyDeEmphasizedAlpha = 0.6f
const val slightlyDeEmphasizedAlpha = 0.87f

/**
 * This wrapper is useful if some style adjustments will be required in the future
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}
