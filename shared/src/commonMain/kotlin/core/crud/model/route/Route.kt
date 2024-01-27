package core.crud.model.route

open class Route(
    val uri: String,
    val authName: String? = null,
    val rbacAuthType: String? = null,
)