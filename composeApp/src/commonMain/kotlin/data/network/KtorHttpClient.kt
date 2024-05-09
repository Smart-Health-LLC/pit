package data.network

import business.BASE_API_URL
import business.CUSTOM_TAG
import data.network.auth.SIGNUP_ROUTE
import data.network.auth.dto.RefreshToken
import domain.repository.SettingsRepository
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

const val HTTP_TIMEOUT = 60000L
const val REFRESH_TOKEN_ROUTE = "/refresh-token"

object KtorHttpClient {
    @OptIn(ExperimentalSerializationApi::class)
    fun httpClient(settingsRepository: SettingsRepository) = HttpClient {
        expectSuccess = false

        install(HttpTimeout) {
            connectTimeoutMillis = HTTP_TIMEOUT
            requestTimeoutMillis = HTTP_TIMEOUT
            socketTimeoutMillis = HTTP_TIMEOUT
        }


        install(Auth) {
            bearer {
                /**
                 * refreshTokens block triggers when server returns 401.
                 * Firstly token refresh request will be sent after that our client will resend
                 * request with new token.
                 *
                 * NOTE: add markAsRefreshTokenRequest() method to HttpRequestBuilder block
                 * for token refresh requests.
                 */
                refreshTokens {
                    val token = client.get {
                        markAsRefreshTokenRequest()
                        url {
                            takeFrom(BASE_API_URL)
                            encodedPath += SIGNUP_ROUTE
                        }
                        parameter("refreshToken", settingsRepository.getRefreshToken())
                    }.body<RefreshToken>()
                    BearerTokens(
                        accessToken = token.bearerToken,
                        refreshToken = token.refreshToken
                    )
                }
            }
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response.status.value}")
            }
        }

        install(Logging) {
            //  logger = Logger.DEFAULT
            level = LogLevel.ALL

            logger = object : Logger {
                override fun log(message: String) {
                    println("AppDebug KtorHttpClient message:$message")
                    Napier.i(tag = CUSTOM_TAG, message = message)
                }
            }.also {
                Napier.base(DebugAntilog())
            }
        }

        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
                encodeDefaults = true
                classDiscriminator = "#class"
            })
        }
    }
}
