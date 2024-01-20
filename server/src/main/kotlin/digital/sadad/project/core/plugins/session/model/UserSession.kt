package digital.sadad.project.core.plugins.session.model

import io.ktor.server.auth.*

data class UserSession(val state: String, val token: String, val count: Int) : Principal