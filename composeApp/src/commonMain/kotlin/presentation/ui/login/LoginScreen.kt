package presentation.ui.login

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class LoginScreen : Screen {

    @Composable
    override fun Content() {

//        val screenModel =
//        val navigator = LocalNavigator.currentOrThrow
//
//        LaunchedEffect(state.navigateToMain) {
//            if (state.navigateToMain) {
//                navigateToMain()
//            }
//        }
//
//        var isUsernameError by rememberSaveable { mutableStateOf(false) }
//        var isPasswordError by rememberSaveable { mutableStateOf(false) }
//
//        val username = remember { mutableStateOf(TextFieldValue()) }
//        val password = remember { mutableStateOf(TextFieldValue()) }
//
//        // ----- Navigate to register
//        Box(modifier = Modifier.fillMaxSize()) {
//            ClickableText(
//                text = AnnotatedString(stringResource(Res.string.already_have_account)),
//                modifier = Modifier
//                    .padding(20.dp),
//                onClick = { navigateToSignup() },
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontFamily = FontFamily.Default,
//                    textDecoration = TextDecoration.Underline,
//                )
//            )
//        }
//        Column(
//            modifier = Modifier.padding(20.dp).fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//
//            Text(
//                text = stringResource(Res.string.login),
//                style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//            TextField(
//                label = { Text(text = stringResource(Res.string.username)) },
//                value = username.value,
//                onValueChange = { username.value = it })
//
//            Spacer(modifier = Modifier.height(20.dp))
//            TextField(
//                label = { Text(text = stringResource(Res.string.password)) },
//                value = password.value,
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                onValueChange = { password.value = it })
//
//            // ------- Login
//            Spacer(modifier = Modifier.height(20.dp))
//            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                Button(
//                    onClick = { },
//                    shape = RoundedCornerShape(50.dp),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp)
//                ) {
//                    Text(text = stringResource(Res.string.login))
//                }
//            }

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
//        }
    }
}