package auth.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginDto(
    val username: String,
    val password: String
)