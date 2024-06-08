package presentation.ui.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import presentation.component.*
import presentation.icon.ArrowBackIcon
import presentation.ui.auth.*

class SignupScreen : Screen {
    @Composable
    override fun Content() {
        SignUpScreenCustom("", { _, _ -> }, {})
    }

}

@Composable
fun SignUpScreenCustom(
    email: String?,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Text(
                        text = "Let's get to know each other",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer_24dp()

                    SignUpContentCustom(
                        email = email,
                        onSignUpSubmitted = onSignUpSubmitted
                    )
                    Spacer_8dp()
                    Row {
                        Text(
                            "Already have an account?",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            "Login",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun SignUpContentCustom(
    email: String?,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
) {

    val passwordFocusRequest = remember { FocusRequester() }
    val confirmationPasswordFocusRequest = remember { FocusRequester() }
    val emailState = remember { EmailState(email) }
    val passwordState = remember { PasswordState() }
    val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer_16dp()
        Password(
            label = "password",
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer_16dp()
        Password(
            label = "confirm password",
            passwordState = confirmPasswordState,
            onImeAction = { onSignUpSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = false, onCheckedChange = {})
            Text(text = "Agree with ", style = MaterialTheme.typography.labelLarge)
            Text(
                text = "terms of service",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer_16dp()
        Button(
            onClick = { onSignUpSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = "create account")
        }
    }
}
