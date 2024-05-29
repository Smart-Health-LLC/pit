package i18n

// https://developer.android.com/guide/topics/resources/providing-resources#LocaleQualifier
/**
 * In-app available IETF BCP47 compliant language tags
 */
object Locales {
    const val EN = "en"
    const val RU = "ru"
}

const val defaultLocale = Locales.RU

/**
 * Class contains distinctive information about existing locales
 *
 * @param tag An IETF BCP47 compliant language tag
 * @param name Human-readable native language name
 */
sealed class LocaleInfo(val tag: String, val name: String) {
    data object English : LocaleInfo(
        tag = Locales.EN,
        name = "English",
    )

    data object Russian : LocaleInfo(
        tag = Locales.RU,
        name = "Русский"
    )
}

fun getLocaleInfo(tag: String?): LocaleInfo {
    return when (tag) {
        LocaleInfo.English.tag -> LocaleInfo.English
        LocaleInfo.Russian.tag -> LocaleInfo.Russian
        else -> {
            getLocaleInfo(defaultLocale) // mom's engineer
        }
    }
}

fun getLocalesInfo(): List<LocaleInfo> {
    return listOf(LocaleInfo.English, LocaleInfo.Russian)
}