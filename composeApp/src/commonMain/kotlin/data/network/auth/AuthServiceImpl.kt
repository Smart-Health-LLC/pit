package data.network.auth

import data.network.BASE_API_URL
import data.network.GenericResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.*
import wtf.auth.dto.AuthRequest

class AuthServiceImpl(
    private val httpClient: HttpClient
) : AuthService {
    override suspend fun login(username: String, password: String): GenericResponse<String?> {
        return httpClient.post {
            url {
                takeFrom(BASE_API_URL)
                encodedPath += LOGIN_ROUTE
            }
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(username = username, password = password))
        }.body()
    }

    override suspend fun register(
        username: String,
        password: String
    ): GenericResponse<String?> {
        return httpClient.post {
            url {
                takeFrom(BASE_API_URL)
                encodedPath += SIGNUP_ROUTE
            }
            contentType(ContentType.Application.Json)
            setBody(AuthRequest(username = username, password = password))
        }.body()
    }
}
