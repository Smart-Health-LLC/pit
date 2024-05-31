package presentation.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.component.KoinComponent
import presentation.component.PasswordTextField
import presentation.component.UsernameTextField
import presentation.ui.login.LoginScreen
import presentation.ui.main.MainScreen


class SignupScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        signupScreen(
            isLoading = false,
            username = "johndoe",
            onUsernameChange = {},
            onPasswordChange = {},
            password = "password",
            confirmPassword = "password",
            onConfirmPasswordChanged = {},
            isValidConfirmPassword = false,
            onNavigateUp = { navigator.replace(LoginScreen()) },
            onSignUpClick = { navigator.replaceAll(MainScreen()) },
            onDialogDismiss = {},
            isValidUsername = false,
            isValidPassword = false,
            error = null
        )
    }

}


@Composable
fun signupScreen(
    isLoading: Boolean,
    username: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    password: String,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    isValidConfirmPassword: Boolean,
    onNavigateUp: () -> Unit,
    onSignUpClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    isValidUsername: Boolean,
    isValidPassword: Boolean,
    error: String?
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = strings.signup,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 60.dp, bottom = 16.dp)
        )

        SignUpForm(
            username = username,
            onUsernameChange = onUsernameChange,
            isValidUsername = isValidUsername,
            password = password,
            onPasswordChange = onPasswordChange,
            isValidPassword = isValidPassword,
            confirmPassword = confirmPassword,
            onConfirmPasswordChanged = onConfirmPasswordChanged,
            isValidConfirmPassword = isValidConfirmPassword,
            onSignUpClick = onSignUpClick
        )

        LoginLink(Modifier.align(Alignment.CenterHorizontally), onLoginClick = onNavigateUp)

        AgreementLink(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {})
    }
}

@Composable
private fun SignUpForm(
    username: String,
    onUsernameChange: (String) -> Unit,
    isValidUsername: Boolean,
    password: String,
    onPasswordChange: (String) -> Unit,
    isValidPassword: Boolean,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    isValidConfirmPassword: Boolean,
    onSignUpClick: () -> Unit
) {
    Column(
        Modifier.padding(
            start = 16.dp,
            top = 32.dp,
            end = 16.dp,
            bottom = 16.dp
        )
    ) {
        UsernameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.background),
            value = username,

            )

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.background),
            value = password,
            onValueChange = onPasswordChange,
            isError = !isValidPassword
        )

        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.background),
            value = confirmPassword,
            label = "Confirm Password",
            onValueChange = onConfirmPasswordChanged,
            isError = !isValidConfirmPassword
        )

        Button(
            onClick = onSignUpClick,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(color = Color.White, text = strings.createAccountButton)
        }
    }
}

@Composable
private fun LoginLink(modifier: Modifier, onLoginClick: () -> Unit) {
    Text(
        text = strings.alreadyHaveAnAccount,
        modifier = modifier
            .clickable(onClick = onLoginClick)
    )
}


@Composable
private fun AgreementLink(modifier: Modifier, onClick: () -> Unit) {
    Text(
        text =
        strings.byContinue,
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
    )
}
