package core.role.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class RoleCreateDto(
    val name: String,
    val description: String? = null,
)
