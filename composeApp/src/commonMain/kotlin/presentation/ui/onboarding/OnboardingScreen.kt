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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.*
import org.koin.core.component.KoinComponent
import pit.composeapp.generated.resources.*
import presentation.component.CustomFullWidthButton

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
                navigator.push(UsernameScreen())
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
                    text = "Get Started",
                    onClick = onClickGetStarted,
                )
            } else {
                CustomFullWidthButton(
                    modifier = Modifier.padding(10.dp),
                    text = "Next",
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

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun OnboardingFirstPage() {
    PageContent(
        title = "Change the way you sleep",
        description = "Adaptation to polyphasic sleep is a lifechanger, but requires some additional forces. Be ready to stress yourself just last time and feel fresh after successful adaptation to reduced sleep schedule with reduced total sleep time per day.",
        illustration = Res.drawable.sleep,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun OnboardingSecondPage() {
    PageContent(
        title = "Keep in mind",
        description = "By using the app you agree to our disclaimer of liability for possible personal injury. Sleep deprivation is not a joke. If you feel yourself bad, don't feel bad about stop the adaptation and start it again. Keep in mind your personal age, health, environment limitations and use the app rationally. We hope you'll do your best anyway",
        illustration = Res.drawable.medical,
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun PageContent(title: String, description: String, illustration: DrawableResource) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(illustration),
            contentDescription = null,
            modifier = Modifier.size(270.dp),
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
//            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.displayLarge.copy(
//                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
//                textAlign = TextAlign.Center,
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
//            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(
//                fontSize = 14.sp,
//                textAlign = TextAlign.Center,
            ),
        )
    }
}

@Composable
fun OnBoardingNavigationButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}
