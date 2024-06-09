package i18n.locale

import cafe.adriel.lyricist.LyricistStrings
import i18n.*
import presentation.ui.change_schedule.ErrorCode


@SuppressWarnings("SpellCheckingInspection")
@LyricistStrings(languageTag = Locales.RU)
val RuStrings = Strings(

    onboardingStrings = OnboardingStrings(
        next = "Далее",
        changeTheWayYouSleep = "Сон по-новому",
        getStarted = "Начать",
        keepInMind = "Внимание",
        firstOnboardingMessage = "Адаптация к полифазному сну меняет жизнь, но требует некоторых дополнительных сил. Будьте готовы напрячься в последний раз и почувствовать себя свежим после успешной адаптации к сокращенному графику сна с уменьшенным общим временем сна в сутки.",
        secondOnboardingMessage = "Используя приложение, вы соглашаетесь с нашим отказом от ответственности за возможные травмы. Недостаток сна - это не шутка. Если вы почувствовали себя плохо, не расстраивайтесь, остановите адаптацию и начните ее снова. Помните о своем возрасте, здоровье, ограничениях окружающей среды и используйте приложение рационально. Мы надеемся, что вы все равно сделаете все возможное.",
    ),

    settingsStrings = SettingsStrings(
        firstTab = "Вкладка при открытии",
        dateAndTime = "Дата и время",
        interfaceSettingsGroup = "Интерфейс",
        dateFormat = "Формат даты",
        appTheme = "Тема приложения",
        appThemeFollowSystem = "Системная",
        durationFormat = "Формат времени",
        settings = "Настройки",
        selectNewLanguage = "Выберите язык",
        timeFormat24h = "24-часовой формат",
        language = "Язык",
        firstDayOfWeek = "Первый день недели",
    ),

    tabNames = TabNames(
        tabHome = "Главная",
        tabAdaptationStats = "Адаптация",
        tabDailyStats = "День",
        tabMore = "Больше",
    ),

    weekDays = WeekDays(
        monday = "Понедельник",
        tuesday = "Вторник",
        wednesday = "Среда",
        thursday = "Четверг",
        friday = "Пятница",
        saturday = "Суббота",
        sunday = "Воскресенье"
    ),

    dialogStrings = DialogStrings(
        dismiss = "Закрыть",
        confirm = "Подтвердить",
        ok = "Ок",
        clearChoice = "Очистить",
        cancel = "Отмена",
        chooseDate = "Выберите дату",
    ),

    authStrings = AuthStrings(
        username = "Логин",
        password = "Пароль",
        confirmPassword = "Подтверждение пароля",
        createAccount = "Регистрация",
        createAccountButton = "Зарегистрироваться",
        alreadyHaveAnAccount = "Уже есть аккаунт? Войти",
        login = "Войти",
        signup = "Новый профиль",
        byContinue = "Продолжая Вы принимаете правила использвания",
        dontHaveAnAccount = "Нет аккаунта? Зарегистрироваться",
        welcomeBack = "Рады Вас видеть",
        termsOfService = "правила использования",
        agreeWith = "Принять "
    ),

    errorDescriptionByCode = {
        when (it) {
            ErrorCode.OVERLAP -> "Сегменты пересекаются"
            ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE -> "Общее время сна нового режима ощутимо отличается от базового"
            ErrorCode.HUGE_AWAKE_TIME -> "Между некоторыми сегментами расстояние больше 6 часов"
            ErrorCode.ANOMALY_LONG_SEGMENT -> "Один из сегментов чрезмерно долгий"
            ErrorCode.SEGMENT_ZERO_DURATION -> "Один из сегментов заканчивается сразу как начался"
        }
    },

    startedAt = "Начат ",
    changeSchedule = "Изменить график",
    napIn = "Сон через",
    tstToday = "TST сегодня",
    streak = "Рекорд",
    chooseAnotherSchedule = "Выбрать другой режим",
    wakeUpEase = "Лёгкость подъёма",
    fallAsleepEase = "Лёгкость засыпания",
    easeMiddle = "Приемлемо",
    easeLow = "Сложно",
    easeHigh = "Легко",
    save = "Сохранить",
    note = "Заметка",
    inDevelopment = "В разработке...",
    lack = "Недостаток",
    excess = "Избыток",
    graphEase = "Лёгкость",
    add = "Добавить",
    delete = "Удалить",
    warnings = "Предупреждения",
    errors = "Ошибки",
    rulesBroken = "Нарушены правила",
    defineSegment = "Определите сегмент",
    profile = "Профиль",
    noInternetConnection = "Нет подключения к интеренетy",
)
