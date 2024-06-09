package i18n

import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import i18n.locale.EnStrings
import i18n.locale.RuStrings
import presentation.ui.change_schedule.ErrorCode


data class AuthStrings(
    val password: String,
    val username: String,
    val confirmPassword: String,
    val createAccount: String,
    val login: String,
    val signup: String,
    val byContinue: String,
    val dontHaveAnAccount: String,
    val welcomeBack: String,
    val createAccountButton: String,
    val alreadyHaveAnAccount: String,
    val agreeWith: String,
    val termsOfService: String,
)

data class DialogStrings(
    val dismiss: String,
    val confirm: String,
    val ok: String,
    val clearChoice: String,
    val cancel: String,
    val chooseDate: String,
)

data class TabNames(
    val tabAdaptationStats: String,
    val tabHome: String,
    val tabDailyStats: String,
    val tabMore: String,
)

data class OnboardingStrings(
    val next: String,
    val getStarted: String,
    val keepInMind: String,
    val changeTheWayYouSleep: String,
    val firstOnboardingMessage: String,
    val secondOnboardingMessage: String,
)

data class SettingsStrings(
    val firstDayOfWeek: String,
    val durationFormat: String,
    val dateFormat: String,
    val firstTab: String,
    val appTheme: String,
    val appThemeFollowSystem: String,
    val selectNewLanguage: String,
    val language: String,
    val settings: String,
    val dateAndTime: String,
    val interfaceSettingsGroup: String,
    val timeFormat24h: String,
)

data class WeekDays(
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String,
)

data class Strings(
    val authStrings: AuthStrings,
    val dialogStrings: DialogStrings,
    val tabNames: TabNames,
    val weekDays: WeekDays,
    val settingsStrings: SettingsStrings,
    val onboardingStrings: OnboardingStrings,

    val startedAt: String,
    val changeSchedule: String,
    val napIn: String,
    val tstToday: String,
    val streak: String,

    val chooseAnotherSchedule: String,

    val wakeUpEase: String,
    val fallAsleepEase: String,

    val easeMiddle: String,
    val easeLow: String,
    val easeHigh: String,

    val note: String,
    val inDevelopment: String,
    val lack: String,
    val excess: String,
    val graphEase: String,
    val add: String,
    val delete: String,
    val save: String,
    val warnings: String,
    val errors: String,
    val errorDescriptionByCode: (code: ErrorCode) -> String,
    val rulesBroken: String,
    val defineSegment: String,
    val profile: String,
    val noInternetConnection: String,
)

val TagToStringsMapper: Map<LanguageTag, Strings> = mapOf(
    Locales.EN to EnStrings,
    Locales.RU to RuStrings,
)


var lyricist: Lyricist<Strings> = Lyricist(defaultLocale, TagToStringsMapper)
