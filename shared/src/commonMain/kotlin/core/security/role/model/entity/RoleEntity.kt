package core.security.role.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.security.role.model.Role
import core.security.role.model.dto.RoleDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [RoleCreateDto::class, RoleDto::class],
    fromClasses = [RoleCreateDto::class, RoleDto::class],
)
class RoleEntity(
    val id: Long? = null,
    name: String,
    description: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : Role(
    name,
    description,
)