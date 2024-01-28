package core.route.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.route.model.Route
import core.route.model.dto.RouteCreateDto
import core.route.model.dto.RouteDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [RouteCreateDto::class, RouteDto::class],
    fromClasses = [RouteCreateDto::class, RouteDto::class],
)
class RouteEntity(
    val id: Long?,
    uri: String,
    authName: String? = null,
    rbacAuthType: String? = null,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
) : Route(uri, authName, rbacAuthType)