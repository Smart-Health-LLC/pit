package domain.repository

import kotlinx.coroutines.flow.Flow

// Work with local data on higher level
interface SettingsRepository {
    suspend fun saveAppTheme(theme: Int)
    fun getAppTheme(): Flow<Int?>
    fun clearAll()
    fun getToken(): Flow<String?>
    fun saveToken(token: String)
    fun getRefreshToken(): Flow<String?>
    fun saveRefreshToken(token: String)
    fun getUsername(): Flow<String?>
    fun saveUsername(username: String)
}
