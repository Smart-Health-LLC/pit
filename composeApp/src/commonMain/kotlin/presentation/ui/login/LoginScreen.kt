package presentation.ui.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import presentation.component.*

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
        ) {
            TopGreeting()

            LoginForm()

            SignUpLink(Modifier.align(Alignment.CenterHorizontally))
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
            text = "Welcome\nback",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
        )
    }
}

@Composable
private fun LoginForm(
) {
    UsernameTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(MaterialTheme.colorScheme.background),
//        value = "asht",
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
        text = "Login",
        onClick = {},
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
    )
}

@Composable
private fun SignUpLink(modifier: Modifier) {
    Text(
        text = buildAnnotatedString {
            append("Don't have an account? Signup")
            addStyle(SpanStyle(color = MaterialTheme.colorScheme.primary), 23, this.length)
            toAnnotatedString()
        },
//        style = typography.subtitle1,
        modifier = modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .clickable(onClick = {})
    )
}
