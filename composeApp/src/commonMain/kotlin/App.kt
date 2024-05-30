import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.voyager.navigator.Navigator
import i18n.defaultLocale
import i18n.lyricist
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.theme.AppTheme
import presentation.ui.main.*

@Composable
fun App(
    mainViewModel: MainViewModel = koinInject(),
) {
    // todo
    val isDarkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    val onBoardingState = mainViewModel.onBoardingState.collectAsState().value
    val currentLangInfo = mainViewModel.languageSetting.collectAsState().value

    ProvideStrings(lyricist, LocalStrings) {
        KoinContext {
            if (currentLangInfo.tag != defaultLocale)
                lyricist.languageTag = currentLangInfo.tag

            AppTheme() {

                when (onBoardingState) {
                    is OnBoardingState.Success -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {

                            Navigator(
                                MainScreen()
//                                RateSegmentScreen()
//                                screen = if (onBoardingState.completed) {
//                                    MainScreen()
//                                } else {
//                                    OnboardingScreen()
//                                },
//                                content = { CurrentScreen() },
                            )
                        }
                    }

                    is OnBoardingState.Loading -> {}
                }
            }
        }
    }
}