package core.security.route.model

import kotlinx.serialization.Serializable

@Serializable
open class Route(
    val uri: String,
    val authName: String? = null,
    val rbacAuthType: String? = null,
)