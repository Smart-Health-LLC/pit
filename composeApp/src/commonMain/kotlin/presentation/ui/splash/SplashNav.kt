package presentation.ui.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import org.koin.compose.koinInject
import presentation.navigation.SplashNavigation
import presentation.ui.splash.view_model.LoginViewModel

@Composable
internal fun SplashNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavController()

    NavHost(
        startDestination = SplashNavigation.Splash.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = SplashNavigation.Splash.route) {
            SplashScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToMain = navigateToMain,
                navigateToLogin = {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login.route)
                })
        }
        composable(route = SplashNavigation.Login.route) {
            LoginScreen(
                navigateToMain = navigateToMain, navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register.route)
                },
                loginState = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
        composable(route = SplashNavigation.Register.route) {
            SignupScreen(
                navigateToMain = navigateToMain, popUp = {
                    navigator.popBackStack()
                }, loginState = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
    }
}
