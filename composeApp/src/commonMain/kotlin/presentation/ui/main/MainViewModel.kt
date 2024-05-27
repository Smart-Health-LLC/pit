package presentation.ui.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.repository.SegmentReportRepository
import domain.repository.SettingsRepository
import i18n.Locales
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import presentation.wtf.CUSTOM_TAG

class MainViewModel(
    settingsRepository: SettingsRepository,
    reportRepository: SegmentReportRepository
) : ScreenModel {

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().map { it }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
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

    val languageSetting: StateFlow<String?> = settingsRepository.getLang().map { it }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Locales.EN
    )
}

sealed class OnBoardingState {
    data object Loading : OnBoardingState()
    data class Success(val completed: Boolean) : OnBoardingState()
}
