package core.userrole.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRoleCreateDto(
    val userId: Long,
    val roleId: Long,
)
