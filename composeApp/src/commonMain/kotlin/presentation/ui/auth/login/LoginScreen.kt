package presentation.ui.auth.login


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import presentation.component.*
import presentation.icon.ArrowBackIcon
import presentation.ui.auth.*

class SignInScreen : Screen {
    @Composable
    override fun Content() {
        SignInScreenCustom({ _, _ -> }, {})
    }
}

@Composable
fun SignInScreenCustom(
    onSignInSubmitted: (email: String, password: String) -> Unit,
    toRegister: () -> Unit,
) {
    Scaffold(
        topBar = {
            IconButton(onClick = toRegister) {
                Icon(
                    imageVector = ArrowBackIcon,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {

                // Screen title
                Text("Welcome back", style = MaterialTheme.typography.headlineMedium)

                Spacer_24dp()

                // Auth Form
                SignInContentCustom(
                    email = null,
                    onSignInSubmitted = onSignInSubmitted,
                )
                Spacer_8dp()

                Row {
                    Text("Don't have and account?", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        "Register",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    )
}

@Composable
fun SignInContentCustom(
    email: String?,
    onSignInSubmitted: (email: String, password: String) -> Unit,
) {
    val passwordState = remember { PasswordState() }
    val focusRequester = remember { FocusRequester() }
    val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
        mutableStateOf(EmailState(email))
    }

    val onSubmit = {
        if (emailState.isValid && passwordState.isValid) {
            onSignInSubmitted(emailState.text, passwordState.text)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Email(emailState, onImeAction = { focusRequester.requestFocus() })

        Spacer_12dp()

        Password(
            label = "password",
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )



        Button(
            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = emailState.isValid && passwordState.isValid
        ) {
            Text(
                text = "sign in"
            )
        }
    }
}

