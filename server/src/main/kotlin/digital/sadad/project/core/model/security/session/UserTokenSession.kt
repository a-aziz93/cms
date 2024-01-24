package digital.sadad.project.core.model.security.session

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenSession(
    val state: String,
    val token: String,
    override val roles: Set<String>?=null,
    override val count: Long,
) : UserSession