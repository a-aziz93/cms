package digital.sadad.project.core.config.model.plugin.security.bearer

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.AuthSchemesConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class BearerAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
    session: SessionAuthConfig? = null,
) : AuthConfig(
    name,
    session,
)