import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import i18n.defaultLocale
import i18n.lyricist
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import presentation.theme.AppTheme
import presentation.ui.auth.login.SignInScreen
import presentation.ui.main.*

@Composable
fun App(
    mainViewModel: MainViewModel = koinInject(),
) {
    val isDarkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }
    val onBoardingState by mainViewModel.onBoardingState.collectAsState()
    val currentLangInfo by mainViewModel.languageSetting.collectAsState()

    ProvideStrings(lyricist, LocalStrings) {
        KoinContext {
            if (currentLangInfo.tag != defaultLocale) {
                lyricist.languageTag = currentLangInfo.tag
            }

            AppTheme(isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    BottomSheetNavigator(
                        sheetGesturesEnabled = true,
                        sheetElevation = BottomSheetDefaults.Elevation,
                        sheetShape = MaterialTheme.shapes.large,
                        sheetContentColor = BottomSheetDefaults.ContainerColor,
                        skipHalfExpanded = false
                    ) {
                        Navigator(
                            when (onBoardingState) {
                                is OnBoardingState.Success -> {
                                    MainScreen()
                                }

                                is OnBoardingState.InProgress -> {
                                    SignInScreen()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
