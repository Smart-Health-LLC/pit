package presentation.ui.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.imageResource
import pit.composeapp.generated.resources.Res
import pit.composeapp.generated.resources.logo_round
import presentation.ui.entry.view_model.AuthEvent
import presentation.ui.entry.view_model.AuthState


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SplashScreen(
    state: AuthState,
    events: (AuthEvent) -> Unit,
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
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageResource(Res.drawable.logo_round),
            "App logo",
            modifier = Modifier.size(180.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            textAlign = TextAlign.Center,
            fontSize = 45.sp,
            text = "Splash screen",
            fontWeight = FontWeight.Bold,
        )
    }

}