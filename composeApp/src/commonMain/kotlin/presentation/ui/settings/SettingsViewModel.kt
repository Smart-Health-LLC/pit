package presentation.ui.settings

import cafe.adriel.voyager.core.model.ScreenModel
import domain.repository.SettingsRepository
import i18n.lyricist


class SettingsViewModel(private val settingsRepository: SettingsRepository) : ScreenModel {

    val timeout = 5_000L


    // update UI language data and lyricist information
    fun updateLanguage(newLanguageTag: String) {
        lyricist.languageTag = newLanguageTag
    }
}