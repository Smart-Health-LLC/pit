import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import di.KoinInit
import org.koin.core.Koin


lateinit var koin: Koin

fun main() {
    koin = KoinInit().init()
    koin.loadModules(
        listOf(),
    )

    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "pit",
            state = rememberWindowState(
                position = WindowPosition.Aligned(Alignment.Center),
                width = 1200.dp,
                height = 700.dp,
            ),
        ) {
            App()
        }
    }
}