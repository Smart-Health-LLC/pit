package presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import domain.repository.SettingsRepository
import i18n.*
import io.github.aakira.napier.log
import org.koin.compose.koinInject
import presentation.icon.ScheduleIcon
import presentation.ui.chooseSchedule.ChooseScheduleScreen
import presentation.ui.main.MainViewModel
import presentation.ui.settings.component.SampleData.items
import presentation.ui.settings.component.SingleChoiceAlertDialog
import presentation.wtf.CUSTOM_TAG

class SettingsScreen() : Screen {

    @Composable
    override fun Content(
    ) {
        SettingsScreenContent()
    }
}

@Composable
fun SettingsScreenContent(
    settingsViewModel: SettingsViewModel = koinInject(),
    mainViewModel: MainViewModel = koinInject(),
    settingsRepository: SettingsRepository = koinInject()
) {
    var expanded by remember { mutableStateOf(false) }
//    var selectedLocaleInfo by remember { mutableStateOf(getLocaleInfo(lyricist.languageTag)) }
    var selectedLocaleInfo = mainViewModel.languageSetting.collectAsState().value

    val singleSelectionState = remember { mutableStateOf(getLocaleInfo(lyricist.languageTag)) }
    val localNavigator = LocalNavigator.currentOrThrow

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            SettingLanguage()
        }
        item {
            SettingsMenuLink(
                title = { Text(text = strings.changeSchedule) },
                subtitle = { Text(text = "Dual Core 1") },
                modifier = Modifier,
                enabled = true,
                icon = { Icon(ScheduleIcon, null) },
                onClick = { localNavigator.push(ChooseScheduleScreen()) },
            )
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {


                Text(text = strings.language)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {

                        TextField(
                            value = selectedLocaleInfo.name,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            getLocalesInfo().forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item.name) },
                                    onClick = {
                                        selectedLocaleInfo = item
                                        lyricist.languageTag = item.tag
                                        settingsRepository.saveLang(item.tag)
                                        expanded = false
                                        log(tag = CUSTOM_TAG) { lyricist.languageTag }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun SettingLanguage(
    onLanguageUpdate: (newLanguageTag: String) -> Unit,
    currentLanguageInfo: LocaleInfo
) {
    val isDialogOpen = remember { mutableStateOf(false) }

    fun toggleDialog() {
        isDialogOpen.value = !isDialogOpen.value
    }

    // @formatter:off
    SettingsMenuLink(
        title    = { Text(text = strings.language) },
        subtitle = { Text(text = currentLanguageInfo.name) },
        onClick  = { toggleDialog() },
        icon     = { Icon(Icons.Filled.Favorite, null) },
    )
    // @formatter:on

    if (isDialogOpen.value) {
        SingleChoiceAlertDialog(
            items = getLocalesInfo(),
            selectedItemKey = currentLanguageInfo,
            onItemSelected = { selectedItemKey ->
                onLanguageUpdate(selectedItemKey)
                toggleDialog()
            },
        )
    }
}
