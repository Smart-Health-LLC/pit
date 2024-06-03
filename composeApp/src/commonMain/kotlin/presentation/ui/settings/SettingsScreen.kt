package presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.alorma.compose.settings.ui.SettingsGroup
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.dokar.sonner.rememberToasterState
import i18n.LocaleInfo
import i18n.getLocalesInfo
import org.koin.compose.koinInject
import presentation.component.ToasterWrapper
import presentation.icon.ScheduleIcon
import presentation.icon.WatchLater
import presentation.ui.choose_schedule.ChooseScheduleScreen
import presentation.ui.main.MainViewModel
import presentation.ui.settings.component.SingleChoiceAlertDialog

class SettingsScreen() : Screen {

    @Composable
    override fun Content(
    ) {
        SettingsScreenContent()
    }
}

@Composable
fun SettingsScreenContent(
    mainViewModel: MainViewModel = koinInject(),
) {
    val selectedLocaleInfo = mainViewModel.languageSetting.collectAsState().value
    val localNavigator = LocalNavigator.currentOrThrow

    val toasterState = rememberToasterState { }
    val toastMessage = strings.inDevelopment
    ToasterWrapper(toasterState)


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            Text(text = strings.settings, style = MaterialTheme.typography.displaySmall)
        }
        item {
            SampleSection(strings.dateAndTime)
            {
                SettingsMenuLink(
                    title = { Text(text = strings.timeFormat24h) },
                    modifier = Modifier,
                    enabled = true,
                    action = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Switch(true, {})
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Outlined.Notifications,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )

                SettingsMenuLink(
                    title = { Text(text = strings.dateFormat) },
                    subtitle = { Text(text = "yyyy-mm-dd") },
                    modifier = Modifier,
                    enabled = true,
                    icon = {
                        Icon(
                            Icons.Outlined.DateRange,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )

                SettingsMenuLink(
                    title = { Text(text = strings.durationFormat) },
                    subtitle = { Text(text = "HH:mm") },
                    modifier = Modifier,
                    enabled = true,
                    icon = {
                        Icon(
                            Icons.Outlined.WatchLater,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )

                SettingsMenuLink(
                    title = { Text(text = strings.firstDayOfWeek) },
                    subtitle = { Text(text = strings.monday) },
                    modifier = Modifier,
                    enabled = true,
                    icon = {
                        Icon(
                            Icons.Outlined.Star,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )
            }
        }

        item {
            SampleSection(strings.interfaceSettingsGroup) {
                SettingLanguage(currentLanguageInfo = selectedLocaleInfo, onLanguageUpdate = {
                    mainViewModel.updateLanguage(it)
                })

                SettingsMenuLink(
                    title = { Text(text = strings.firstTab) },
                    subtitle = { Text(text = strings.tabDailyStats) },
                    modifier = Modifier,
                    enabled = true,
                    icon = {
                        Icon(
                            Icons.Outlined.Place,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )

                SettingsMenuLink(
                    title = { Text(text = strings.appTheme) },
                    subtitle = { Text(text = strings.appThemeFollowSystem) },
                    modifier = Modifier,
                    enabled = true,
                    icon = {
                        Icon(
                            Icons.Outlined.Build,
                            null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onClick = {
                        toasterState.show(toastMessage)
                    },
                )
            }
        }
        item {
            SettingsMenuLink(
                title = { Text(text = strings.changeSchedule) },
                subtitle = { Text(text = "Dual Core 1") },
                modifier = Modifier,
                enabled = true,
                icon = {
                    Icon(
                        ScheduleIcon,
                        null,
                        modifier = Modifier.size(25.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                onClick = { localNavigator.push(ChooseScheduleScreen()) },
            )
        }
    }
}


@Composable
private fun SettingLanguage(
    onLanguageUpdate: (newLanguage: LocaleInfo) -> Unit,
    currentLanguageInfo: LocaleInfo
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val rememberedCurrentLanguageInfo = remember { mutableStateOf(currentLanguageInfo) }

    fun toggleDialog() {
        isDialogOpen.value = !isDialogOpen.value
    }

    // @formatter:off
    SettingsMenuLink(
        title    = { Text(text = strings.language) },
        subtitle = { Text(text = rememberedCurrentLanguageInfo.value.name) },
        onClick  = { toggleDialog() },
        icon     = { Icon(Icons.Filled.Favorite, null, tint = MaterialTheme.colorScheme.primary ) },
    )
    // @formatter:on

    if (isDialogOpen.value) {
        SingleChoiceAlertDialog(
            items = getLocalesInfo(),
            previouslySelectedLanguage = rememberedCurrentLanguageInfo.value,
            onItemSelected = { newLanguage ->
                onLanguageUpdate(newLanguage)
                toggleDialog()
                rememberedCurrentLanguageInfo.value = newLanguage
            },
            onDialogDismiss = { toggleDialog() }
        )
    }
}

@Composable
internal fun SampleSection(
    title: String,
    enabled: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    SettingsGroup(
        enabled = enabled,
        title = { Text(text = title) },
    ) {
        ElevatedCard { content() }
    }
}