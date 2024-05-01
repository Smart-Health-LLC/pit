class JVMWebRouter : WebRouter {

    override fun openUrl(url: String) {
    }
}

actual fun openWebPage(): WebRouter = JVMWebRouter()
