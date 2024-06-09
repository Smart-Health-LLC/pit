package presentation.ui.onboarding


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.core.component.KoinComponent
import pit.composeapp.generated.resources.*
import presentation.component.*
import presentation.ui.auth.register.RegisterScreen

class OnboardingScreen : Screen, KoinComponent {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()
        val pageCount = 2
        val pagerState = rememberPagerState(pageCount = { pageCount })

        OnboardingScreenContent(
            pagerState = pagerState,
            pageCount = pageCount,
            onClickNext = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
            onClickGetStarted = {
                navigator.push(RegisterScreen())
            },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent(
    pageCount: Int,
    pagerState: PagerState,
    onClickNext: () -> Unit,
    onClickGetStarted: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (pagerState.currentPage == pageCount - 1) {
                CustomFullWidthButton(
                    modifier = Modifier.padding(10.dp),
                    text = strings.onboardingStrings.getStarted,
                    onClick = onClickGetStarted,
                )
            } else {
                CustomFullWidthButton(
                    modifier = Modifier.padding(10.dp),
                    text = strings.onboardingStrings.next,
                    onClick = onClickNext,
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {

            Spacer(Modifier.padding(10.dp))

            PageIndicators(
                pageCount = pageCount,
                currentPage = pagerState.currentPage,
            )


            HorizontalPager(
                modifier = Modifier
                    .weight(.9f)
                    .padding(16.dp),
                state = pagerState,
            ) { currentPage ->
                when (currentPage) {
                    0 -> OnboardingFirstPage()
                    1 -> OnboardingSecondPage()
                }
            }

        }
    }
}

@Composable
private fun ColumnScope.PageIndicators(pageCount: Int, currentPage: Int) {
    Row(
        Modifier
            .weight(.1f)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(pageCount) { iteration ->
            val color =
                if (currentPage == iteration) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.2f,
                    )
                }
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .width(24.dp)
                    .height(8.dp),

                )
        }
    }
}

@Composable
private fun OnboardingFirstPage() {
    PageContent(
        title = strings.onboardingStrings.changeTheWayYouSleep,
        description = strings.onboardingStrings.firstOnboardingMessage,
        illustration = Res.drawable.sleep,
    )
}

@Composable
private fun OnboardingSecondPage() {
    PageContent(
        title = strings.onboardingStrings.keepInMind,
        description = strings.onboardingStrings.secondOnboardingMessage,
        illustration = Res.drawable.medical,
    )
}

@Composable
private fun PageContent(title: String, description: String, illustration: DrawableResource) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(illustration),
            contentDescription = null,
            modifier = Modifier.size(270.dp),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
        )
        Spacer_32dp()
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer_12dp()
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}