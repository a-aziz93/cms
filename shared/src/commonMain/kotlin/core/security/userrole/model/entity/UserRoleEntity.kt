package core.security.userrole.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.security.userrole.model.UserRole
import core.security.userrole.model.dto.UserRoleDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [UserRole::class, UserRoleDto::class],
    fromClasses = [UserRole::class, UserRoleDto::class],
)
class UserRoleEntity(
    val id: Long? = null,
    userId: Long,
    roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : UserRole(
    userId,
    roleId
)