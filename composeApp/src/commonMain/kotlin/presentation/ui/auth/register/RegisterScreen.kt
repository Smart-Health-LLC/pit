package presentation.ui.auth.register

import androidx.compose.foundation.clickable
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
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.component.*
import presentation.icon.ArrowBackIcon
import presentation.ui.auth.*
import presentation.ui.auth.login.SignInScreen

class RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val localNavigator = LocalNavigator.currentOrThrow
        RegisterScreenCustom("", { _, _ -> }, { localNavigator.replace(SignInScreen()) })
    }
}

@Composable
fun RegisterScreenCustom(
    email: String?,
    onSignUpSubmitted: (email: String, password: String) -> Unit,
    toLoginScreen: () -> Unit,
) {
    Surface {

        Scaffold(
            topBar = {
                IconButton(onClick = {}) {
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
                                strings.authStrings.alreadyHaveAnAccount,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                strings.authStrings.login,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable {
                                    toLoginScreen()
                                }
                            )
                        }
                    }
                }
            }
        )
    }
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
    val localNavigator = LocalNavigator.currentOrThrow

    Column(modifier = Modifier.fillMaxWidth()) {
        Email(emailState, onImeAction = { passwordFocusRequest.requestFocus() })

        Spacer_16dp()
        Password(
            label = strings.authStrings.password,
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = { confirmationPasswordFocusRequest.requestFocus() },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer_16dp()
        Password(
            label = strings.authStrings.confirmPassword,
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
            Text(text = strings.authStrings.agreeWith, style = MaterialTheme.typography.labelLarge)
            Text(
                text = strings.authStrings.termsOfService,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    localNavigator.push(TermsOfServiceScreen())
                }
            )
        }

        Spacer_16dp()
        Button(
            onClick = { onSignUpSubmitted(emailState.text, passwordState.text) },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = strings.authStrings.createAccount)
        }
    }
}
