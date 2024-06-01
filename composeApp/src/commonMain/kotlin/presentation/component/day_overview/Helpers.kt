package presentation.component.day_overview

import androidx.compose.ui.graphics.Color

fun getContrastColor(color: Color): Color {
    // Extract the red, green, and blue components from the color
    val red = color.red
    val green = color.green
    val blue = color.blue

    // Calculate the luminance of the color
    val luminance = (0.299 * red + 0.587 * green + 0.114 * blue) / 255

    // Return black or white based on the luminance
    return if (luminance > 0.5) darkTextColor else lightTextColor
}
