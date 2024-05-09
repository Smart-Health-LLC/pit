package presentation.ui.main

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.repository.SettingsRepository
import kotlinx.coroutines.flow.*

class MainViewModel(
    settingsRepository: SettingsRepository,
) : ScreenModel {

    val appTheme: StateFlow<Int?> = settingsRepository.getAppTheme().map { it }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

    val onBoardingState: StateFlow<OnBoardingState> =
        settingsRepository.getUsername().map {
            OnBoardingState.Success(it.isNullOrEmpty().not())
        }.stateIn(
            scope = screenModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = OnBoardingState.Loading,
        )
}

sealed class OnBoardingState {
    data object Loading : OnBoardingState()
    data class Success(val completed: Boolean) : OnBoardingState()
}
