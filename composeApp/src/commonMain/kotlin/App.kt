import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import domain.repository.SettingsRepository
import i18n.lyricist
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.theme.AppTheme
import presentation.ui.main.*
import presentation.ui.onboarding.OnboardingScreen
import presentation.wtf.ProvideAppNavigator

@Composable
fun App(
    mainViewModel: MainViewModel = koinInject(),
    settingsRepository: SettingsRepository = koinInject()
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
                                    MainScreen()
                                } else {
                                    OnboardingScreen()
                                },
                                content = { navigator ->
                                    ProvideAppNavigator(
                                        navigator = navigator,
                                        content = { SlideTransition(navigator = navigator) },
                                    )
                                },
                            )
                        }
                    }
                    // todo I completely lost in this magic "when" block
                    else -> {}
                }
            }


        }
    }
}