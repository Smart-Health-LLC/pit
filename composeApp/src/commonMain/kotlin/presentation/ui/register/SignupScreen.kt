package presentation.ui.register

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SignupScreen() {

//    LaunchedEffect(loginState.navigateToMain) {
//        if (loginState.navigateToMain) {
//            navigateToMain()
//        }
//    }
//
//    // ----- to login page
//    Box(modifier = Modifier.fillMaxSize()) {
//        ClickableText(
//            text = AnnotatedString(stringResource(Res.string.to_login_page)),
//            modifier = Modifier
//                .padding(20.dp),
//            onClick = { popUp() },
//            style = TextStyle(
//                fontSize = 14.sp,
//                fontFamily = FontFamily.Default,
//                textDecoration = TextDecoration.Underline,
//            )
//        )
//    }
//    Column(
//        modifier = Modifier.padding(20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        val username = remember { mutableStateOf(TextFieldValue()) }
//        val password = remember { mutableStateOf(TextFieldValue()) }
//
//        Text(
//            text = stringResource(Res.string.signup),
//            style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(
//            label = { Text(text = stringResource(Res.string.username)) },
//            value = username.value,
//            onValueChange = { username.value = it })
//
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(
//            label = { Text(text = stringResource(Res.string.password)) },
//            value = password.value,
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            onValueChange = { password.value = it })
//
//        // ---- signup
//        Spacer(modifier = Modifier.height(20.dp))
//        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//            Button(
//                onClick = { events(AuthEvent.Register) },
//                shape = RoundedCornerShape(50.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//            ) {
//                Text(text = stringResource(Res.string.signup))
//            }
//        }
//    }
}
