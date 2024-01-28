package core.route.model

open class Route(
    val uri: String,
    val authName: String? = null,
    val rbacAuthType: String? = null,
)