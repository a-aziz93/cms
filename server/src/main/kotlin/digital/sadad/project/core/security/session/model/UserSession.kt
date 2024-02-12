package digital.sadad.project.core.security.session.model

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val principal: Principal,
    val roles: Set<String>?,
    val count: Long,
)