package digital.sadad.project.core.model.security

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserIdPrincipalMetadata(
    val username: String,
    val roles: Set<String>? = null,
) : Principal