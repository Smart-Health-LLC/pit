import androidx.compose.ui.window.ComposeUIViewController
import di.KoinManager

fun MainViewController() = ComposeUIViewController {
    KoinManager.startKoin()
    App()
}