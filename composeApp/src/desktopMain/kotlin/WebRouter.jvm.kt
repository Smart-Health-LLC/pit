import java.awt.Desktop
import java.net.URI

class JVMWebRouter : WebRouter {

    override fun openUrl(url: String) {
        Desktop.getDesktop().browse(URI(url))
    }
}

actual fun openWebPage(): WebRouter = JVMWebRouter()
