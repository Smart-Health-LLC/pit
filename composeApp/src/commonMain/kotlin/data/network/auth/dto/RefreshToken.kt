package data.network.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(val token: String)