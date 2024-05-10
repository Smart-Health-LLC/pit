package presentation.ui.onboarding


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject
import org.koin.core.component.KoinComponent
import presentation.ui.main.MainScreen
import presentation.wtf.UiEvents

class UsernameScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val onboardingViewModel = koinInject<OnboardingViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val username = onboardingViewModel.username.collectAsState().value
        val keyboardController = LocalSoftwareKeyboardController.current

        LaunchedEffect(Unit) {
            withContext(Dispatchers.Main.immediate) {
                onboardingViewModel.eventsFlow.collect { event ->
                    when (event) {
                        is UiEvents.Navigation -> {
                            navigator.replaceAll(MainScreen())
                        }

                        else -> {}
                    }
                }
            }
        }
        UsernameScreenContent(
            username = username,
            onUsernameChange = {
                onboardingViewModel.setUsername(it)
            },
            onClickContinue = {
                keyboardController?.hide()
                onboardingViewModel.saveUsername()
            },
        )
    }
}

@Composable
fun UsernameScreenContent(
    username: String,
    onUsernameChange: (String) -> Unit,
    onClickContinue: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {

        item {
            Spacer(modifier = Modifier.height(56.dp))
            Text(
                text = "What's your username?",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 18.sp,
                ),
            )
        }

        item {
            val focusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            UsernameTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                name = username,
                onNameChange = {
                    onUsernameChange(it)
                },
                onClickDone = {
                    onClickContinue()
                },
            )
        }

        item {
            AnimatedVisibility(
                username.isNotEmpty(),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(56.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = MaterialTheme.shapes.medium,
                        onClick = onClickContinue,
                    ) {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UsernameTextField(
    modifier: Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    onClickDone: () -> Unit,
) {
    TextField(
        modifier = modifier,
        value = name,
        onValueChange = onNameChange,
        maxLines = 1,
        singleLine = true,
        placeholder = {
            Text(
                text = "Username",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 18.sp,
                    letterSpacing = -(1.6).sp,
                    lineHeight = 32.sp,
                ),
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            disabledIndicatorColor = MaterialTheme.colorScheme.primary,
        ),
        textStyle = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Words,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onClickDone()
            },
        ),
    )
}
