interface WebRouter {
    fun openUrl(url: String)
}


expect fun openWebPage(): WebRouter