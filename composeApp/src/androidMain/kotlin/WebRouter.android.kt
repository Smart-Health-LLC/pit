import android.content.Context
import android.content.Intent
import android.net.Uri
import di.KoinManager

class AndroidWebPageRouter : WebRouter {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = Uri.parse(url)
        }
        KoinManager().getKoinInstance().get<Context>().apply {
            startActivity(intent)
        }
    }
}

actual fun openWebPage(): WebRouter = AndroidWebPageRouter()