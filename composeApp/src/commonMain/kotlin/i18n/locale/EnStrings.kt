package i18n.locale

import cafe.adriel.lyricist.LyricistStrings
import i18n.Locales
import i18n.Strings
import presentation.ui.change_schedule.ErrorCode

@LyricistStrings(languageTag = Locales.EN, default = true)
val EnStrings = Strings(
    startedAt = "Started at ",
    changeSchedule = "Change schedule",
    napIn = "Sleep in",
    tstToday = "TST today",
    streak = "Streak",
    changeTheWayYouSleep = "Change the way you sleep",
    firstOnboardingMessage = "Adaptation to polyphasic sleep is a lifechanger, but requires some additional forces. Be ready to stress yourself just last time and feel fresh after successful adaptation to reduced sleep schedule with reduced total sleep time per day.",
    secondOnboardingMessage = "By using the app you agree to our disclaimer of liability for possible personal injury. Sleep deprivation is not a joke. If you feel yourself bad, don't feel bad about stop the adaptation and start it again. Keep in mind your personal age, health, environment limitations and use the app rationally. We hope you'll do your best anyway",
    next = "Next",
    getStarted = "Get started",
    username = "Username",
    password = "Password",
    keepInMind = "Keep in mind",
    confirmPassword = "Confirm password",
    createAccount = "Create account",
    createAccountButton = "Create account",
    alreadyHaveAnAccount = "Already have an account? Login",
    login = "Login",
    signup = "Sign up",
    byContinue = "By signing up you are apply the service agreement",
    dontHaveAnAccount = "Don't have an account? Signup",
    welcomeBack = "Welcome back",
    language = "Language",
    chooseAnotherSchedule = "Choose another schedule",
    tabHome = "Home",
    tabAdaptationStats = "Adaptation",
    tabDailyStats = "Day",
    tabMore = "More",
    timeFormat24h = "Use 24-hour clock",
    wakeUpEase = "Wake up ease level",
    fallAsleepEase = "Fall asleep ease level",
    easeMiddle = "Viable",
    easeLow = "Hard",
    easeHigh = "Easy",
    save = "Save",
    note = "Note",
    dismiss = "Dismiss",
    confirm = "Confirm",
    ok = "Ok",
    inDevelopment = "In development...",
    firstDayOfWeek = "First day of the week",
    dateFormat = "Date format",
    durationFormat = "Duration format",
    firstTab = "First tab",
    appTheme = "App theme",
    appThemeFollowSystem = "Follow system",
    cancel = "Cancel",
    selectNewLanguage = "Select language",
    clearChoice = "Clear",
    monday = "Monday",
    settings = "Settings",
    lack = "Lack",
    excess = "Excess",
    graphEase = "Adaptation ease",
    chooseDate = "Choose date",
    add = "Add",
    delete = "Delete",
    warnings = "Warnings",
    errors = "Errors",
    errorDescriptionByCode = {
        when (it) {
            ErrorCode.OVERLAP -> "Segments overlap"
            ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE -> "The time of the new mode is perceptibly shorter than the base mode"
            ErrorCode.HUGE_AWAKE_TIME -> "Some segments are more than 6 hours apart"
            ErrorCode.ANOMALY_LONG_SEGMENT -> "One of the segments is extremely long"
        }
    },
    rulesBroken = "Rules broken",
    defineSegment = "Define segment"
)
