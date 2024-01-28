package digital.sadad.project.core.security.service.session.model

import kotlinx.serialization.Serializable

@Serializable
data class UsernameSession(
    val username: String,
    override val roles: Set<String>? = null,
    override val count: Long,
) : UserSession
