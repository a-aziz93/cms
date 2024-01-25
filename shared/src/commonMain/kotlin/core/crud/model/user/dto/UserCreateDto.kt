package core.crud.model.user.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCreateDto(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val avatar: String?,
    val active: Boolean = false,
)