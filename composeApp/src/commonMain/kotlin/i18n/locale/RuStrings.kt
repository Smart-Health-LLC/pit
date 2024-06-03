package i18n.locale

import cafe.adriel.lyricist.LyricistStrings
import i18n.Locales
import i18n.Strings
import presentation.ui.change_schedule.ErrorCode


@SuppressWarnings("SpellCheckingInspection")
@LyricistStrings(languageTag = Locales.RU)
val RuStrings = Strings(
    startedAt = "Начат ",
    changeSchedule = "Изменить график",
    napIn = "Сон через",
    tstToday = "TST сегодня",
    streak = "Рекорд",
    changeTheWayYouSleep = "Сон по-новому",
    firstOnboardingMessage = "Адаптация к полифазному сну меняет жизнь, но требует некоторых дополнительных сил. Будьте готовы напрячься в последний раз и почувствовать себя свежим после успешной адаптации к сокращенному графику сна с уменьшенным общим временем сна в сутки.",
    secondOnboardingMessage = "Используя приложение, вы соглашаетесь с нашим отказом от ответственности за возможные травмы. Недостаток сна - это не шутка. Если вы почувствовали себя плохо, не расстраивайтесь, остановите адаптацию и начните ее снова. Помните о своем возрасте, здоровье, ограничениях окружающей среды и используйте приложение рационально. Мы надеемся, что вы все равно сделаете все возможное.",
    next = "Далее",
    getStarted = "Начать",
    username = "Логин",
    password = "Пароль",
    keepInMind = "Внимание",
    confirmPassword = "Подтверждение пароля",
    createAccount = "Регистрация",
    createAccountButton = "Зарегистрироваться",
    alreadyHaveAnAccount = "Уже есть аккаунт? Войти",
    login = "Войти",
    signup = "Новый профиль",
    byContinue = "Продолжая Вы принимаете правила использвания",
    dontHaveAnAccount = "Нет аккаунта? Зарегистрироваться",
    welcomeBack = "Рады Вас видеть",
    language = "Язык",
    chooseAnotherSchedule = "Выбрать другой режим",
    tabHome = "Главная",
    tabAdaptationStats = "Адаптация",
    tabDailyStats = "День",
    tabMore = "Больше",
    timeFormat24h = "24-часовой формат",
    wakeUpEase = "Лёгкость подъёма",
    fallAsleepEase = "Лёгкость засыпания",
    easeMiddle = "Приемлемо",
    easeLow = "Сложно",
    easeHigh = "Легко",
    save = "Сохранить",
    note = "Заметка",
    dismiss = "Закрыть",
    confirm = "Подтвердить",
    ok = "Ок",
    inDevelopment = "В разработке...",
    firstDayOfWeek = "Первый день недели",
    dateFormat = "Формат даты",
    durationFormat = "Формат времени",
    firstTab = "Вкладка при открытии",
    appTheme = "Тема приложения",
    appThemeFollowSystem = "Системная",
    cancel = "Отмена",
    clearChoice = "Очистить",
    selectNewLanguage = "Выберите язык",
    monday = "Понедельник",
    settings = "Настройки",
    lack = "Недостаток",
    excess = "Избыток",
    graphEase = "Лёгкость",
    chooseDate = "Выберите дату",
    add = "Добавить",
    delete = "Удалить",
    warnings = "Предупреждения",
    errors = "Ошибки",
    errorDescriptionByCode = {
        when (it) {
            ErrorCode.OVERLAP -> "Сегменты пересекаются"
            ErrorCode.TST_STRONGLY_DIFFERS_FROM_BASE -> "Общее время сна нового режима ощутимо отличается от базового"
            ErrorCode.HUGE_AWAKE_TIME -> "Между некоторыми сегментами расстояние больше 6 часов"
            ErrorCode.ANOMALY_LONG_SEGMENT -> "Один из сегментов чрезмерно долгий"
            ErrorCode.SEGMENT_ZERO_DURATION -> "Один из сегментов заканчивается сразу как начался"
        }
    },
    rulesBroken = "Нарушены правила",
    defineSegment = "Определите сегмент",
    profile = "Профиль",
    dateAndTime = "Дата и время",
    interfaceSettingsGroup = "Интерфейс"
)
