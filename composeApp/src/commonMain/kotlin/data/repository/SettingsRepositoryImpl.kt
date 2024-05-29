package data.repository

import data.local.PreferenceManager
import domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow


class SettingsRepositoryImpl(
    private val preferenceManager: PreferenceManager,
) : SettingsRepository {
    // todo make theme not int
    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = PreferenceManager.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.APP_THEME)
    }

    override fun clearAll() {
        return preferenceManager.clearPreferences()
    }

    // maybe combine refresh and access token in one data object
    override fun getToken(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun getRefreshToken(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override fun saveRefreshToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun getUsername(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.USERNAME)
    }

    override fun saveUsername(username: String) {
        preferenceManager.setString(key = PreferenceManager.USERNAME, value = username)
    }

    override fun saveLang(lang: String) {
        preferenceManager.setString(key = PreferenceManager.LANGUAGE_ID, value = lang)
    }

    override fun getLang(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.LANGUAGE_ID)
    }

    override fun getHourFormat(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.HOUR_FORMAT)
    }

    override fun saveHourFormat(timeFormat: Int) {
        preferenceManager.setInt(key = PreferenceManager.HOUR_FORMAT, value = timeFormat)
    }
}
