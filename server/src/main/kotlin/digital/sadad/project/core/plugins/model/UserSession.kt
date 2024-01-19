package digital.sadad.project.core.plugins.model

import io.ktor.server.auth.*

data class UserSession(val state: String, val token: String) : Principal