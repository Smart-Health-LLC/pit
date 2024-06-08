package data.repository

import data.local.PreferenceManager
import domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow


class SettingsRepositoryImpl(
    private val preferenceManager: PreferenceManager,
) : SettingsRepository {


    override fun clearAll() {
        return preferenceManager.clearPreferences()
    }

    // Theme
    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = PreferenceManager.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.APP_THEME)
    }


    // Last token
    override fun getLastToken(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.LAST_JWT)
    }

    override fun saveLastToken(token: String) {
        preferenceManager.setString(key = PreferenceManager.LAST_JWT, value = token)
    }


    // Refresh token
    override fun getRefreshToken(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.REFRESH_TOKEN)
    }

    override fun saveRefreshToken(token: String) {
        preferenceManager.setString(key = PreferenceManager.REFRESH_TOKEN, value = token)
    }

    // Password
    override fun getPassword(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.PASSWORD)
    }

    override fun savePassword(password: String) {
        preferenceManager.setString(key = PreferenceManager.PASSWORD, value = password)
    }


    // Login
    override fun getLogin(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.LOGIN)
    }

    override fun saveLogin(login: String) {
        preferenceManager.setString(key = PreferenceManager.LOGIN, value = login)
    }


    // Language
    override fun saveLang(lang: String) {
        preferenceManager.setString(key = PreferenceManager.LANGUAGE_TAG, value = lang)
    }

    override fun getLang(): Flow<String?> {
        return preferenceManager.getString(key = PreferenceManager.LANGUAGE_TAG)
    }


    // Hour format
    override fun getHourFormat(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.HOUR_FORMAT)
    }

    override fun saveHourFormat(timeFormat: Int) {
        preferenceManager.setInt(key = PreferenceManager.HOUR_FORMAT, value = timeFormat)
    }
}
