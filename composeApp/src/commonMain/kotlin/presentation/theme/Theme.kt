package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

const val stronglyDeEmphasizedAlpha = 0.6f
const val slightlyDeEmphasizedAlpha = 0.87f

object Margins {
    /**
     * Window widths smaller than 600dp, such as a phone in portrait orientation.
     */
    val compact = 15.dp

    /**
     * Window widths from 600dp to 839dp, such as a tablet or foldable in portrait orientation.
     */
    val medium = 30.dp

    /**
     * Window widths 840dp to 1199dp, such as a tablet or foldable in landscape orientation, or desktop.
     */
    val expanded = 60.dp

    /**
     * Window widths 1200dp to 1599dp, such as desktop.
     */
    val large = 100.dp

    /**
     * Window widths 1600dp and larger, such as ultra-wide monitors.
     */
    val extraLarge = 250.dp
}

// https://material-foundation.github.io/material-theme-builder/
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
        content = content
    )
}
