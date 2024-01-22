package digital.sadad.project.auth.model.security

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class UserPrincipal(
    val username: String,
    val token: String,
    val count: Int
) : Principal