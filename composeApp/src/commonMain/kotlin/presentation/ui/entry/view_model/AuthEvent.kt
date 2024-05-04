package presentation.ui.entry.view_model

import business.NetworkState

sealed class AuthEvent {
    data class OnUpdateNameRegister(val value: String) : AuthEvent()
    data class OnUpdateUsernameLogin(val value: String) : AuthEvent()
    data class OnUpdatePasswordLogin(val value: String) : AuthEvent()
    data object Register : AuthEvent()
    data object Login : AuthEvent()
    data object OnRetryNetwork : AuthEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : AuthEvent()
}
