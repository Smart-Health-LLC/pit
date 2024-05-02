package presentation.ui.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import presentation.ui.splash.view_model.LoginEvent
import presentation.ui.splash.view_model.LoginState

@Composable
internal fun SplashScreen(
    state: LoginState,
    events: (LoginEvent) -> Unit,
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(state.navigateToMain) {
        // todo remove stupid delay
        delay(1000L)
        if (state.navigateToMain) {
            navigateToMain()
        } else {
            navigateToLogin()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                "splash screen",
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                "somestring",
                fontWeight = FontWeight.Bold,
            )
        }
    }

}