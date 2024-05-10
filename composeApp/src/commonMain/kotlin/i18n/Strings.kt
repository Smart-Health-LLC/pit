package i18n

import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import i18n.locale.EnStrings
import i18n.locale.RuStrings

data class Strings(
    val simple: String,
)

val TagToStringsMapper: Map<LanguageTag, Strings> = mapOf(
    Locales.EN to EnStrings,
    Locales.RU to RuStrings,
)

var lyricist: Lyricist<Strings> = Lyricist(Locales.EN, TagToStringsMapper)
