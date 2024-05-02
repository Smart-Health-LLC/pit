import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import org.koin.compose.KoinContext
import presentation.navigation.AppNavigation
import presentation.theme.AppTheme
import presentation.ui.main.MainNav
import presentation.ui.splash.SplashNav

@Composable
fun App() {
    KoinContext {
        AppTheme {
            val navigator = rememberNavController()

            // todo in depends of whether user logged in or not navigate to login screen or main screen

            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Splash.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = AppNavigation.Splash.route) {
                        SplashNav(navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Main.route)
                        })
                    }
                    composable(route = AppNavigation.Main.route) {
                        MainNav {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Splash.route)
                        }
                    }
                }
            }
        }
    }

}