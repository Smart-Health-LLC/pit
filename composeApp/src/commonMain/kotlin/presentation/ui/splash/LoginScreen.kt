package presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import presentation.ui.splash.view_model.LoginEvent
import presentation.ui.splash.view_model.LoginState

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    events: (LoginEvent) -> Unit,
    loginState: LoginState,
    navigateToMain: () -> Unit,
    navigateToRegister: () -> Unit,
) {


    LaunchedEffect(loginState.navigateToMain) {
        if (loginState.navigateToMain) {
            navigateToMain()
        }
    }


    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login Screen",
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(5.dp),
                onClick = {
                    // todo navigate further
                    events(LoginEvent.Login)
                }
            ) {
                Text(
                    text = "Go to Home Screen",
                    modifier = Modifier.padding(5.dp),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "go to register",
                )
                Text(
                    modifier = Modifier.clickable {
                        navigateToRegister()
                    },
                    text = "common register bro",
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}