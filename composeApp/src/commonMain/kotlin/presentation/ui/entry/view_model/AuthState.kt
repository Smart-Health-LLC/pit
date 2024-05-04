package presentation.ui.entry.view_model

import business.NetworkState
import business.ProgressBarState

data class AuthState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,
    val navigateToMain: Boolean = false,

    val networkState: NetworkState = NetworkState.Good,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
)