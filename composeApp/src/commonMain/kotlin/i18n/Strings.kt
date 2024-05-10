package i18n

import cafe.adriel.lyricist.Lyricist

data class Strings(
    val simple: String,
)


var lyricist: Lyricist<Strings> = Lyricist("en", TagToStringsMapper)

