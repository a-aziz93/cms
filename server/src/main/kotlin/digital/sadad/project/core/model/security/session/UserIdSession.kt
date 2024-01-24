package digital.sadad.project.core.model.security.session

import kotlinx.serialization.Serializable

@Serializable
data class UserIdSession(
    val username: String,
    override val roles: Set<String>? = null,
    override val count: Long,
) : UserSession
