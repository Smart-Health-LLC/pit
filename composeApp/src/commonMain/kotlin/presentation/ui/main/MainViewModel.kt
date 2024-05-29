package presentation.ui.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.repository.SegmentReportRepository
import domain.repository.SettingsRepository
import i18n.*
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import presentation.wtf.CUSTOM_TAG

class MainViewModel(
    private val settingsRepository: SettingsRepository,
    reportRepository: SegmentReportRepository // for dev dummy tests
) : ScreenModel {

    private val timeout = 5_000L

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().map { it }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(timeout),
        initialValue = null,
    )

    init {
        Napier.i(tag = CUSTOM_TAG) { "here we go" }
        screenModelScope.launch {
            reportRepository.getReports().collect {
                Napier.i(tag = CUSTOM_TAG) { it.toString() }
            }
        }
    }

    val onBoardingState: StateFlow<OnBoardingState> =
        settingsRepository.getUsername().map {
            OnBoardingState.Success(it.isNullOrEmpty().not())
        }.stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = OnBoardingState.Loading,
        )

    // todo this shit now don't emit new values when updateLanguage() executes
    var languageSetting = settingsRepository.getLang()
        .map {
            getLocaleInfo(it)
        }.stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(timeout),
            initialValue = getLocaleInfo(defaultLocale)
        )

    // update UI language data and lyricist information
    fun updateLanguage(newLanguage: LocaleInfo) {
        lyricist.languageTag = newLanguage.tag
        screenModelScope.launch {
            settingsRepository.saveLang(newLanguage.tag)
        }
    }
}

sealed class OnBoardingState {
    data object Loading : OnBoardingState()
    data class Success(val completed: Boolean) : OnBoardingState()
}
