package digital.sadad.project.auth.model.security

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserIDPrincipal(
    val username: String,
    val roles: Set<String>? = null,
) : Principal