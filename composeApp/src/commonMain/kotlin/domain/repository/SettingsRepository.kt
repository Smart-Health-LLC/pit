package domain.repository

import kotlinx.coroutines.flow.Flow

// Work with local key-value data on higher level
interface SettingsRepository {
    fun clearAll()

    fun getPassword(): Flow<String?>
    fun savePassword(password: String)

    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>

    fun getLastToken(): Flow<String?>
    fun saveLastToken(token: String)

    fun getRefreshToken(): Flow<String?>
    fun saveRefreshToken(token: String)

    fun getLogin(): Flow<String?>
    fun saveLogin(login: String)

    fun saveLang(lang: String)
    fun getLang(): Flow<String?>

    fun getHourFormat(): Flow<Int?>
    fun saveHourFormat(timeFormat: Int)
}
