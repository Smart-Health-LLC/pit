package presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import domain.repository.SettingsRepository
import i18n.*
import io.github.aakira.napier.log
import org.koin.compose.koinInject
import presentation.ui.chooseSchedule.ChooseScheduleScreen
import presentation.wtf.CUSTOM_TAG

class SettingsScreen() : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(
    ) {
        // todo move it to viewmodel
        val screenModel: SettingsViewModel = koinInject()
        val settingsRepository: SettingsRepository = koinInject()

        var expanded by remember { mutableStateOf(false) }
        var selectedLocaleInfo by remember { mutableStateOf(getLocale(lyricist.languageTag)) }
        val localNavigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            horizontalAlignment = Alignment.Start
        ) {

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
                            getLocales().forEach { item ->
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
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                onClick = {
                    localNavigator.push(ChooseScheduleScreen())
                }
            ) {
                Text(strings.chooseAnotherSchedule)
            }
        }
    }
}

