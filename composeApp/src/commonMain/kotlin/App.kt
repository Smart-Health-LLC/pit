import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.voyager.navigator.Navigator
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import i18n.defaultLocale
import i18n.lyricist
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.theme.AppTheme
import presentation.ui.main.MainViewModel
import presentation.ui.main.OnBoardingState
import presentation.ui.no_internet.NoInternetScreen


fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).components { }.build()

@OptIn(ExperimentalCoilApi::class)
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

                setSingletonImageLoaderFactory { context ->
                    getAsyncImageLoader(context)
                }

                when (onBoardingState) {
                    is OnBoardingState.Success -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {

                            Navigator(
//                                MainScreen()
                                NoInternetScreen()
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