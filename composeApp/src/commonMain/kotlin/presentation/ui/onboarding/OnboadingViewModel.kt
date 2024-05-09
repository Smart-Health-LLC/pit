package presentation.ui.onboarding

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.repository.SettingsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import presentation.wtf.UiEvents

class OnboardingViewModel(
    private val settingsRepository: SettingsRepository,
) : ScreenModel {
    private val _eventsFlow = Channel<UiEvents>(Channel.UNLIMITED)
    val eventsFlow = _eventsFlow.receiveAsFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()
    fun setUsername(username: String) {
        _username.value = username
    }

    fun saveUsername() {
        screenModelScope.launch {
            settingsRepository.saveUsername(username.value.trim())
            _eventsFlow.send(UiEvents.Navigation)
        }
    }
}
