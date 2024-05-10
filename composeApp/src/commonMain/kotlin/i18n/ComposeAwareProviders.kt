package i18n

import androidx.compose.runtime.*
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import i18n.locale.EnStrings
import i18n.locale.RuStrings

val TagToStringsMapper: Map<LanguageTag, Strings> = mapOf(
    Locales.EN to EnStrings,
    Locales.RU to RuStrings,
)

@Composable
fun rememberStrings(
    defaultLanguageTag: LanguageTag = Locales.EN,
    currentLanguageTag: LanguageTag,
): Lyricist<Strings> =
    cafe.adriel.lyricist.rememberStrings(TagToStringsMapper, defaultLanguageTag, currentLanguageTag)


val LocalStrings: ProvidableCompositionLocal<Strings> =
    staticCompositionLocalOf { EnStrings }


@Composable
fun ProvideStrings(
    lyricist: Lyricist<Strings>,
    content: @Composable () -> Unit
) {
    cafe.adriel.lyricist.ProvideStrings(lyricist, LocalStrings, content)
}
