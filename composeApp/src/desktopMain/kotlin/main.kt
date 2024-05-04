import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import di.KoinInit
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
import org.koin.core.Koin
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.logo_round


lateinit var koin: Koin

@OptIn(ExperimentalResourceApi::class)
fun main() {
    koin = KoinInit().init()
    koin.loadModules(
        listOf(),
    )
    Napier.base(DebugAntilog())

    return application {
        Window(
            icon = BitmapPainter(image = imageResource(Res.drawable.logo_round)),
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