package wtf.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: TokenResponse,
)

