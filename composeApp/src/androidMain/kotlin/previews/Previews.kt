package previews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import presentation.ui.main.HomeScreen
import presentation.ui.splash.*
import presentation.ui.splash.view_model.LoginState

// Context-Aware Previews: Light and Dark Theme
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
annotation class ThemePreviews


// Device Orientations Previews: Portrait & Landscape
@Preview(
    name = "Landscape Mode",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    widthDp = 640
)
@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PIXEL_4)
annotation class OrientationPreviews


// Font Scaling Previews: Different accessibility settings
@Preview(name = "Default Font Size", fontScale = 1f)
@Preview(name = "Large Font Size", fontScale = 1.5f)
annotation class FontScalePreviews

// Layout Directions Previews: LTR & RTL support for international apps
@Preview(name = "Left-To-Right", locale = "en")
@Preview(name = "Right-To-Left", locale = "ar")
annotation class LayoutDirectionPreviews

@ThemePreviews
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        loginState = LoginState(),
        navigateToMain = {},
        navigateToRegister = {},
        events = {})
}

@ThemePreviews
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}


@ThemePreviews
@Composable
fun SignupScreenPreview() {
    SignupScreen(loginState = LoginState(), navigateToMain = {}, events = {}, popUp = {})
}


@ThemePreviews
@Composable
fun SplashScreenPreview() {
    SplashScreen(state = LoginState(), navigateToLogin = {}, navigateToMain = {}, events = {})
}

