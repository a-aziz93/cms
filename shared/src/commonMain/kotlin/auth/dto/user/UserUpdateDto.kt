package auth.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateDto(
    val name: String,
    val email: String,
    val username: String,
)