package digital.sadad.project.core.service.security.session.model

import kotlinx.serialization.Serializable

@Serializable
data class UserTokenSession(
    val state: String,
    val token: String,
    override val roles: Set<String>?=null,
    override val count: Long,
) : UserSession