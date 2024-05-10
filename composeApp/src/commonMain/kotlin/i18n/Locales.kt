package i18n

// https://developer.android.com/guide/topics/resources/providing-resources#LocaleQualifier
object Locales {
    const val EN = "en"
    const val RU = "ru"
}

sealed class LocalesInfo(val tag: String, val name: String) {
    data object English : LocalesInfo(
        tag = Locales.EN,
        name = "English"
    )

    data object Russian : LocalesInfo(
        tag = Locales.RU,
        name = "Русский"
    )
}

data class Locale(val tag: String, val name: String)

fun getLocale(tag: String): LocalesInfo {
    return when (tag) {
        LocalesInfo.English.tag -> LocalesInfo.English
        LocalesInfo.Russian.tag -> LocalesInfo.Russian
        else -> {
            LocalesInfo.English
        }
    }
}

fun getLocales(): List<LocalesInfo> {
    return listOf(LocalesInfo.English, LocalesInfo.Russian)
}