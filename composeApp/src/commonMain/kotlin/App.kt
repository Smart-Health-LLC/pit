import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import org.koin.compose.KoinContext
import presentation.navigation.InitialNavDestination
import presentation.theme.AppTheme
import presentation.ui.entry.SplashNav
import presentation.ui.main.MainNav

@Composable
fun App() {
    KoinContext {
        AppTheme {
            val navigator = rememberNavController()

            // todo in depends of whether user logged in or not navigate to login screen or main screen

            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navigator,
                    startDestination = InitialNavDestination.Splash.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = InitialNavDestination.Splash.route) {
                        SplashNav(navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(InitialNavDestination.Main.route)
                        })
                    }
                    composable(route = InitialNavDestination.Main.route) {
                        MainNav {
                            navigator.popBackStack()
                            navigator.navigate(InitialNavDestination.Splash.route)
                        }
                    }
                }
            }
        }
    }

}