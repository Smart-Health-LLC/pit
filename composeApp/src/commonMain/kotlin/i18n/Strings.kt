package i18n

import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import i18n.locale.EnStrings
import i18n.locale.RuStrings

data class Strings(
    val startedAt: String,
    val changeSchedule: String,
    val napIn: String,
    val tstToday: String,
    val streak: String,
    val changeTheWayYouSleep: String,
    val firstOnboardingMessage: String,
    val secondOnboardingMessage: String,
    val next: String,
    val getStarted: String,
    val username: String,
    val password: String,
    val keepInMind: String,
    val confirmPassword: String,
    val createAccount: String,
    val createAccountButton: String,
    val alreadyHaveAnAccount: String,
    val login: String,
    val signup: String,
    val byContinue: String,
    val dontHaveAnAccount: String,
    val welcomeBack: String,
    val language: String,
    val chooseAnotherSchedule: String,
    val tabHome: String,
    val tabAdaptationStats: String,
    val tabDailyStats: String,
    val tabMore: String,
    val timeFormat24h: String,
    val wakeUpEase: String,
    val fallAsleepEase: String,
    val easeMiddle: String,
    val easeLow: String,
    val easeHigh: String,
    val save: String,
    val note: String,
    val dismiss: String,
    val confirm: String,
    val ok: String,
    val inDevelopment: String,
    val firstDayOfWeek: String,
    val durationFormat: String,
    val dateFormat: String,
    val firstTab: String,
    val appTheme: String,
    val appThemeFollowSystem: String,
    val selectNewLanguage: String,
    val cancel: String,
    val clearChoice: String,
    val monday: String,
    val settings: String,
    val lack: String,
    val excess: String,
    val graphEase: String
)

val TagToStringsMapper: Map<LanguageTag, Strings> = mapOf(
    Locales.EN to EnStrings,
    Locales.RU to RuStrings,
)


var lyricist: Lyricist<Strings> = Lyricist(defaultLocale, TagToStringsMapper)
