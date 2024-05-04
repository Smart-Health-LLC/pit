package presentation.ui.entry.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import business.NetworkState
import io.github.aakira.napier.Napier


class AuthViewModel(
) : ViewModel() {


    val state: MutableState<AuthState> = mutableStateOf(AuthState())


    init {
        checkToken()
    }


    fun onTriggerEvent(event: AuthEvent) {
        when (event) {

            // Actions
            is AuthEvent.Login -> {
                login()
            }

            is AuthEvent.Register -> {
                register()
            }

            // Data update
            is AuthEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is AuthEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is AuthEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }


            // Update network state
            is AuthEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is AuthEvent.OnUpdateNetworkState -> {
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
