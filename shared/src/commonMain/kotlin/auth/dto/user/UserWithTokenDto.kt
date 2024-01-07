package auth.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserWithTokenDto(
    val user: UserDto,
    val token: String
)