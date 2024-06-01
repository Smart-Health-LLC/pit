package presentation.extention

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Duration

/**
 * Support wide screen by making the content width max 840dp, centered horizontally.
 */
fun Modifier.supportWideScreen() = this
    .fillMaxWidth()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .widthIn(max = 840.dp)

// maybe add full labels ans seconds idk
fun Duration.toFancyString(
    hourShortLabel: String? = null,
    minuteShortLabel: String? = null
): String {
//    val format = if ()
    return String.format(
        "%02d:%02d",
        this.toHours(),
        this.toMinutes() % 60
    )
}