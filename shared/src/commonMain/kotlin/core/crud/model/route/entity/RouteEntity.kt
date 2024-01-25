package core.crud.model.route.entity

import core.crud.model.route.Route
import java.time.LocalDateTime

class RouteEntity(
    val id: Long?,
    uri: String,
    val createdBy: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedBy: String? = null,
    val updatedAt: LocalDateTime? = null
) : Route(uri)