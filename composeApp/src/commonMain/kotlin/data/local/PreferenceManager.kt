package data.local

import com.russhwolf.settings.*
import com.russhwolf.settings.coroutines.*
import kotlinx.coroutines.flow.Flow

// Work with local data on lower level
class PreferenceManager constructor(private val settings: Settings) {
    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings }

    fun setString(key: String, value: String) {
        observableSettings.set(key = key, value = value)
    }


    fun getNonFlowString(key: String) = observableSettings.getString(
        key = key,
        defaultValue = "",
    )

    @OptIn(ExperimentalSettingsApi::class)
    fun getString(key: String) = observableSettings.getStringOrNullFlow(key = key)

    fun setInt(key: String, value: Int) {
        observableSettings.set(key = key, value = value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    fun getInt(key: String) = observableSettings.getIntOrNullFlow(key = key)

    @OptIn(ExperimentalSettingsApi::class)
    fun getIntFlow(key: String) = observableSettings.getIntFlow(key = key, defaultValue = 0)

    // @formatter:off
    companion object {
        const val LOGIN          = "login_key"
        const val PASSWORD       = "password_key"
        const val LAST_JWT       = "last_jwt_key"
        const val REFRESH_TOKEN  = "refresh_token_key"

        const val DATE_FORMAT    = "date_format_key"
        const val TIME_FORMAT    = "time_format_key"
        const val HOUR_FORMAT    = "hour_format_key"
        const val FIRST_WEEK_DAY = "first_week_day_id"

        const val FIRST_TAB      = "first_tab_key"
        const val APP_THEME      = "app_theme_key"
        const val LANGUAGE_TAG   = "language_tag"
    }
    // @formatter:on

    fun clearPreferences() {
        observableSettings.clear()
    }

    @OptIn(ExperimentalSettingsApi::class)
    fun getBoolean(key: String): Flow<Boolean> {
        return observableSettings.getBooleanFlow(
            key = key,
            defaultValue = false,
        )
    }

    fun setBoolean(key: String, value: Boolean) {
        observableSettings.set(key = key, value = value)
    }

    @OptIn(ExperimentalSettingsApi::class)
    fun getLong(key: Any): Flow<Long?> {
        return observableSettings.getLongFlow(
            key = key.toString(),
            defaultValue = 0,
        )
    }

    fun setLong(key: String, value: Long) {
        observableSettings.set(key = key, value = value)
    }
}
