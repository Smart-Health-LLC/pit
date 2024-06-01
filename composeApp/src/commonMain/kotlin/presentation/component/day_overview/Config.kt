package presentation.component.day_overview

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import java.time.format.DateTimeFormatter

val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

// Color for text that is used in segment blocks
val lightTextColor: Color = lightColorScheme().onSurface
val darkTextColor: Color = darkColorScheme().onSurface