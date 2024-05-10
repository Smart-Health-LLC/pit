package presentation.wtf

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator

// https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal
val LocalAppNavigator: ProvidableCompositionLocal<Navigator?> =
    staticCompositionLocalOf { null }

@Composable
fun ProvideAppNavigator(navigator: Navigator, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalAppNavigator provides navigator) {
        content()
    }
}
