package presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.dokar.sonner.rememberToasterState
import i18n.LocaleInfo
import i18n.getLocalesInfo
import org.koin.compose.koinInject
import presentation.component.ToasterWrapper
import presentation.icon.ScheduleIcon
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
    ToasterWrapper(toasterState)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            Text(text = "Настройки", style = MaterialTheme.typography.displaySmall)
        }

        item {
            SettingLanguage(currentLanguageInfo = selectedLocaleInfo, onLanguageUpdate = {
                mainViewModel.updateLanguage(it)
            })
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
        item {
            SettingsMenuLink(
                title = { Text(text = strings.timeFormat) },
                modifier = Modifier,
                enabled = true,
                action = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "12h", style = MaterialTheme.typography.bodyLarge)
                        Switch(true, {})
                        Text(text = "24h", style = MaterialTheme.typography.bodyLarge)
                    }
                },
                icon = {
                    Icon(
                        Icons.Outlined.Notifications,
                        null,
                        tint = MaterialTheme.colorScheme.primary
//                        modifier = Modifier.size(25.dp)
                    )
                },
                onClick = {
                    toasterState.show("Not supported yet")
                },
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
                // HAHAHHA what a stupid shit ooooh boi enough
                rememberedCurrentLanguageInfo.value = newLanguage
            },
            onDialogDismiss = { toggleDialog() }
        )
    }
}
