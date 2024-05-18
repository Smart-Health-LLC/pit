package presentation.ui.register

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import presentation.component.PasswordTextField
import presentation.component.UsernameTextField


class SignupScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        signupScreen(
            isLoading = false,
            username = "johndoe",
            onUsernameChange = {},
            onPasswordChange = {},
            password = "password",
            confirmPassword = "password",
            onConfirmPasswordChanged = {},
            isValidConfirmPassword = false,
            onNavigateUp = {},
            onSignUpClick = {},
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
            text = "Create\naccount",
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
            Text(color = Color.White, text = "Create account")
        }
    }
}

@Composable
private fun LoginLink(modifier: Modifier, onLoginClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            append("Already have an account? Login")
            addStyle(SpanStyle(color = MaterialTheme.colorScheme.primary), 24, this.length)
        },
        modifier = modifier
            .clickable(onClick = onLoginClick)
    )
}


@Composable
private fun AgreementLink(modifier: Modifier, onClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            append("By signing up you are apply an service agreement")
            addStyle(SpanStyle(color = MaterialTheme.colorScheme.primary), 30, this.length)
        },
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
    )
}
