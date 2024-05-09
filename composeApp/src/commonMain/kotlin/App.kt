import androidx.compose.runtime.Composable
import org.koin.compose.KoinContext
import presentation.theme.AppTheme

@Composable
fun App() {
    KoinContext {
        AppTheme {

        }
    }
}