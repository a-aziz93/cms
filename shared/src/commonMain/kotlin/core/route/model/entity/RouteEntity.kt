package core.route.model.entity

import com.github.yanneckreiss.kconmapper.annotations.KConMapper
import core.route.model.Route
import core.route.model.dto.RouteDto
import java.time.LocalDateTime

@KConMapper(
    toClasses = [Route::class, RouteDto::class],
    fromClasses = [Route::class, RouteDto::class],
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