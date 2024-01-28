package core.user.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginDto(
    val username: String,
    val password: String,
)