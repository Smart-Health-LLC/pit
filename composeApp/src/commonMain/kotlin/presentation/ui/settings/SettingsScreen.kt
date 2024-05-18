package presentation.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import data.CUSTOM_TAG
import domain.repository.SettingsRepository
import i18n.*
import io.github.aakira.napier.log
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    screenModel: SettingsViewModel = koinInject(),
    settingsRepository: SettingsRepository = koinInject()

) {
    var expanded by remember { mutableStateOf(false) }
    var selectedLocaleInfo by remember { mutableStateOf(getLocale(lyricist.languageTag)) }

    Text(text = strings.simple)
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
