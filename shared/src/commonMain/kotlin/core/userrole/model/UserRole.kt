package core.userrole.model

import kotlinx.serialization.Serializable

@Serializable
open class UserRole(
    val userId: Long,
    val roleId: Long,
)