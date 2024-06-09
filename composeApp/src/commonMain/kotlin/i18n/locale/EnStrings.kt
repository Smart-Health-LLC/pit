package i18n.locale

import cafe.adriel.lyricist.LyricistStrings
import i18n.*
import presentation.ui.change_schedule.ErrorCode

@LyricistStrings(languageTag = Locales.EN, default = true)
val EnStrings = Strings(

    onboardingStrings = OnboardingStrings(
        next = "Next",
        changeTheWayYouSleep = "Change the way you sleep",
        getStarted = "Get started",
        keepInMind = "Keep in mind",
        firstOnboardingMessage = "Adaptation to polyphasic sleep is a lifechanger, but requires some additional forces. Be ready to stress yourself just last time and feel fresh after successful adaptation to reduced sleep schedule with reduced total sleep time per day.",
        secondOnboardingMessage = "By using the app you agree to our disclaimer of liability for possible personal injury. Sleep deprivation is not a joke. If you feel yourself bad, don't feel bad about stop the adaptation and start it again. Keep in mind your personal age, health, environment limitations and use the app rationally. We hope you'll do your best anyway",
    ),

    settingsStrings = SettingsStrings(
        firstTab = "First tab",
        dateAndTime = "Date and time",
        interfaceSettingsGroup = "Interface",
        dateFormat = "Date format",
        appTheme = "App theme",
        appThemeFollowSystem = "Follow system",
        durationFormat = "Duration format",
        settings = "Settings",
        selectNewLanguage = "Select language",
        timeFormat24h = "Use 24-hour clock",
        language = "Language",
        firstDayOfWeek = "First day of the week",
    ),

    tabNames = TabNames(
        tabHome = "Home",
        tabAdaptationStats = "Adaptation",
        tabDailyStats = "Day",
        tabMore = "More",
    ),

    weekDays = WeekDays(
        monday = "Monday",
        tuesday = "Tuesday",
        wednesday = "Wednesday",
        thursday = "Thursday",
        friday = "Friday",
        saturday = "Saturday",
        sunday = "Sunday"
    ),

    dialogStrings = DialogStrings(
        dismiss = "Dismiss",
        confirm = "Confirm",
        ok = "Ok",
        clearChoice = "Clear",
        cancel = "Cancel",
        chooseDate = "Choose date",
    ),

    authStrings = AuthStrings(
        username = "Username",
        password = "Password",
        confirmPassword = "Confirm password",
        createAccount = "Create account",
        createAccountButton = "Create account",
        alreadyHaveAnAccount = "Already have an account? Login",
        login = "Login",
        signup = "Sign up",
        byContinue = "By signing up you are apply the service agreement",
        dontHaveAnAccount = "Don't have an account? Signup",
        welcomeBack = "Welcome back",
        termsOfService = "terms of service",
        agreeWith = "Agree with ",
    ),


    errorDescriptionByCode = {
        when (it) {
            ErrorCode.OVERLAP -> "Segments overlap"
            ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE -> "Total sleep time of the new schedule is perceptibly different than the base schedule"
            ErrorCode.HUGE_AWAKE_TIME -> "Some segments are more than 6 hours apart"
            ErrorCode.ANOMALY_LONG_SEGMENT -> "One of the segments is extremely long"
            ErrorCode.SEGMENT_ZERO_DURATION -> "One of the segments ends in the moment of the start"
        }
    },


    startedAt = "Started at ",
    changeSchedule = "Change schedule",
    napIn = "Sleep in",
    tstToday = "TST today",
    streak = "Streak",
    chooseAnotherSchedule = "Choose another schedule",
    wakeUpEase = "Wake up ease level",
    fallAsleepEase = "Fall asleep ease level",
    easeMiddle = "Viable",
    easeLow = "Hard",
    easeHigh = "Easy",
    save = "Save",
    note = "Note",
    inDevelopment = "In development...",
    lack = "Lack",
    excess = "Excess",
    graphEase = "Adaptation ease",
    add = "Add",
    delete = "Delete",
    warnings = "Warnings",
    errors = "Errors",

    rulesBroken = "Rules broken",
    defineSegment = "Define segment",
    profile = "Profile",
    noInternetConnection = "No internet connection",
)
