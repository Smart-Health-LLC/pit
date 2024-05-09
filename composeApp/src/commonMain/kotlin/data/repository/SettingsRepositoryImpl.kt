package data.repository

import data.PreferenceManager
import domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow


class SettingsRepositoryImpl(
    private val preferenceManager: PreferenceManager,
) : SettingsRepository {
    override suspend fun saveAppTheme(theme: Int) {
        preferenceManager.setInt(key = PreferenceManager.APP_THEME, value = theme)
    }

    override fun getAppTheme(): Flow<Int?> {
        return preferenceManager.getInt(key = PreferenceManager.APP_THEME)
    }

    override fun clearAll() {
        return preferenceManager.clearPreferences()
    }

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

}
