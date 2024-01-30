package core.routerole.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.routerole.model.RouteRole
import core.routerole.model.dto.RouteRoleDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [RouteRole::class, RouteRoleDto::class],
    fromClasses = [RouteRole::class, RouteRoleDto::class],
)
class RouteRoleEntity(
    val id: Long?=null,
    userId: Long,
    roleId: Long,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null,
) : RouteRole(
    userId,
    roleId,
)