package core.routerole.model

import kotlinx.serialization.Serializable

@Serializable
open class RouteRole(
    val routeId: Long,
    val roleId: Long,
)