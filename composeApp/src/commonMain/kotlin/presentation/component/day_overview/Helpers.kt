package presentation.component.day_overview

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


// Color for text that is used in segment blocks
val lightTextColor: Color = darkColorScheme().onSurface
val darkTextColor: Color = lightColorScheme().onSurface


// https://stackoverflow.com/questions/50540301/c-sharp-get-good-color-for-label/50541212#50541212
fun getContrastColor(
    color: Color,
): Color {
    // Extract the red, green, and blue components from the color
    val red = color.red
    val green = color.green
    val blue = color.blue

    // Calculate the luminance of the color
    val luminance = (0.299 * red + 0.587 * green + 0.114 * blue) / 256

    // Return black or white based on the luminance
    return if (luminance < 0.55) lightTextColor else darkTextColor
}
