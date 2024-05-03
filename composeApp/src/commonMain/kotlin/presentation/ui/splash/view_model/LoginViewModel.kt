package presentation.ui.splash.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import business.NetworkState
import io.github.aakira.napier.Napier


class LoginViewModel(
) : ViewModel() {


    val state: MutableState<LoginState> = mutableStateOf(LoginState())


    init {
        checkToken()
    }


    fun onTriggerEvent(event: LoginEvent) {
        when (event) {

            // Actions
            is LoginEvent.Login -> {
                login()
            }

            is LoginEvent.Register -> {
                register()
            }

            // Data update
            is LoginEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }


            // Update network state
            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }


    private fun checkToken() {
        // todo check token
        Napier.i(tag = "PitDev", message = "checking token")
    }

    private fun login() {
        // todo perform login process
        Napier.i(tag = "PitDev", message = "performing login")
    }

    private fun register() {
        // todo perform register process
        Napier.i(tag = "PitDev", message = "performing signup")
    }


    private fun onUpdateNameRegister(value: String) {
        state.value = state.value.copy(nameRegister = value)
    }


    private fun onUpdatePasswordLogin(value: String) {
        state.value = state.value.copy(passwordLogin = value)
    }


    private fun onUpdateUsernameLogin(value: String) {
        state.value = state.value.copy(usernameLogin = value)
    }


    private fun onRetryNetwork() {
        Napier.i(tag = "PitDev", message = "performing on retry network")
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}
