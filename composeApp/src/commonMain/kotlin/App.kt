import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import i18n.lyricist
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.theme.AppTheme
import presentation.ui.main.MainViewModel
import presentation.ui.main.OnBoardingState
import presentation.ui.onboarding.OnboardingScreen
import presentation.ui.rate.RateSegmentScreen

@Composable
fun App(
    mainViewModel: MainViewModel = koinInject(),
) {
    val isDarkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    val onBoardingState = mainViewModel.onBoardingState.collectAsState().value
    val currentLang = mainViewModel.languageSetting.collectAsState().value

    ProvideStrings(lyricist, LocalStrings) {
        KoinContext {
            if (currentLang != null)
                lyricist.languageTag = currentLang
            AppTheme(darkTheme = isDarkTheme) {

                when (onBoardingState) {
                    is OnBoardingState.Success -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {

                            Navigator(
                                screen = if (onBoardingState.completed) {
//                                    MainScreen()

                                    RateSegmentScreen()
                                } else {
                                    OnboardingScreen()
                                },
                                content = { CurrentScreen() },
                            )
                        }
                    }

                    is OnBoardingState.Loading -> {}
                }
            }
        }
    }
}