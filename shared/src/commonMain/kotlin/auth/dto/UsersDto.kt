package auth.dto

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

/**
 * User DTO for response
 */
@Serializable
data class UserDto(
    val id: Long?,
    val name: String,
    val email: String,
    val username: String,
    val roles: List<String>,
    val avatar: String?,
    val createdBy: String?,
    val createdAt: LocalDateTime?,
    val updatedBy: String?,
    val updatedAt: LocalDateTime?,
    val active: Boolean
)

/**
 * User DTO for request to create a new user
 */
@Serializable
data class UserCreateDto(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String?,
    val roles: List<Long>,
)

/**
 * User DTO for request to update a user
 */
@Serializable
data class UserUpdateDto(
    val name: String,
    val email: String,
    val username: String,
)

/**
 * User DTO for request to login a user
 */
@Serializable
data class UserLoginDto(
    val username: String,
    val password: String
)

/**
 * User DTO for response with token
 */
@Serializable
data class UserWithTokenDto(
    val user: UserDto,
    val token: String
)