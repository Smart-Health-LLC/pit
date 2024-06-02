package presentation.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.dokar.sonner.Toaster
import com.dokar.sonner.ToasterState

@Composable
fun ToasterWrapper(state: ToasterState) {
    Toaster(
        state = state,
        maxVisibleToasts = 1,
        alignment = Alignment.TopCenter,
        darkTheme = isSystemInDarkTheme(),
        swipeable = true
    )
}
