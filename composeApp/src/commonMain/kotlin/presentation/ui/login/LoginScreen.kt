package presentation.ui.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.component.*
import presentation.ui.main.MainScreen
import presentation.ui.register.SignupScreen

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
        ) {
            TopGreeting()

            LoginForm()

            SignUpLink(
                goToSignUpClick = { navigator.push(SignupScreen()) },
                Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
private fun TopGreeting() {
    Column(Modifier.fillMaxWidth()) {
//        Image(
//            contentDescription = "App Logo",
//            painter = painterResource(id = R.drawable.avatar_placeholder),
//            modifier = Modifier
//                .padding(top = 60.dp)
//                .requiredSize(92.dp)
//                .align(Alignment.CenterHorizontally),
//            contentScale = ContentScale.FillBounds
//        )

        Text(
            text = strings.welcomeBack,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
        )
    }
}

@Composable
private fun LoginForm(

) {
    val navigator = LocalNavigator.currentOrThrow

    val username = remember { null }
    val password = remember { null }

    UsernameTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(MaterialTheme.colorScheme.background),
//        value = username,
    )

    PasswordTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(MaterialTheme.colorScheme.background),
//        value = password,
        onValueChange = {},
//        isError = !isValidPassword
    )

    CustomFullWidthButton(
        text = strings.login,
        onClick = {
            navigator.push(MainScreen())
        },
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    )
}

@Composable
private fun SignUpLink(goToSignUpClick: () -> Unit, modifier: Modifier) {
    Text(
        text =
        strings.dontHaveAnAccount,
//        style = typography.subtitle1,
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .clickable(onClick = goToSignUpClick)
    )
}
