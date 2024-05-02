package presentation.ui.splash.view_model

import business.NetworkState

sealed class LoginEvent {


    data class OnUpdateNameRegister(val value: String) : LoginEvent()
    data class OnUpdateUsernameLogin(val value: String) : LoginEvent()
    data class OnUpdatePasswordLogin(val value: String) : LoginEvent()

    data object Register : LoginEvent()
    data object Login : LoginEvent()
    data object OnRemoveHeadFromQueue : LoginEvent()

    data object OnRetryNetwork : LoginEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : LoginEvent()
}
