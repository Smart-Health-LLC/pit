package presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import business.ProgressBarState
import cafe.adriel.voyager.core.model.ScreenModel
import data.network.NetworkState
import data.usecase.LoginUseCaseImpl
import io.github.aakira.napier.Napier


data class AuthState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,
    val navigateToMain: Boolean = false,

    val networkState: NetworkState = NetworkState.Good,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
)


class AuthViewModel(
    private val loginUseCase: LoginUseCaseImpl,
) : ScreenModel {


    val state: MutableState<AuthState> = mutableStateOf(AuthState())

    init {
        checkToken()
    }

    public fun checkToken() {
        // todo check token
        Napier.i(tag = "PitDev", message = "checking token")
    }

    public fun login() {
        // todo perform login process
        Napier.i(tag = "PitDev", message = "performing login")
    }

    public fun register() {
        // todo perform register process
        Napier.i(tag = "PitDev", message = "performing signup")
    }


    public fun onUpdateNameRegister(value: String) {
        state.value = state.value.copy(nameRegister = value)
    }


    public fun onUpdatePasswordLogin(value: String) {
        state.value = state.value.copy(passwordLogin = value)
    }


    public fun onUpdateUsernameLogin(value: String) {
        state.value = state.value.copy(usernameLogin = value)
    }


    public fun onRetryNetwork() {
        Napier.i(tag = "PitDev", message = "performing on retry network")
    }


    public fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }
}
