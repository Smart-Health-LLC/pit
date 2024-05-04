package presentation.ui.entry

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import pit.composeapp.generated.resources.*
import presentation.ui.entry.view_model.AuthEvent
import presentation.ui.entry.view_model.AuthState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    triggerAuthEvent: (AuthEvent) -> Unit,
    state: AuthState,
    navigateToMain: () -> Unit,
    navigateToSignup: () -> Unit,
) {

    LaunchedEffect(state.navigateToMain) {
        if (state.navigateToMain) {
            navigateToMain()
        }
    }

    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }

    // ----- Navigate to register
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString(stringResource(Res.string.already_have_account)),
            modifier = Modifier
                .padding(20.dp),
            onClick = { navigateToSignup() },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )
    }
    Column(
        modifier = Modifier.padding(20.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(
            text = stringResource(Res.string.login),
            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(Res.string.username)) },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = stringResource(Res.string.password)) },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        // ------- Login
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { triggerAuthEvent(AuthEvent.Login) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(Res.string.login))
            }
        }

        // ---- forgot password
//        Spacer(modifier = Modifier.height(20.dp))
//        ClickableText(
//            text = AnnotatedString("Forgot password?"),
//            onClick = { },
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily.Default
//            )
//        )
    }
}