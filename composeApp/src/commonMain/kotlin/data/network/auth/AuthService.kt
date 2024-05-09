package data.network.auth

import data.network.GenericResponse

interface AuthService {
    suspend fun login(username: String, password: String): GenericResponse<String?>

    suspend fun register(
        username: String,
        password: String
    ): GenericResponse<String?>
}