package platform

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}
