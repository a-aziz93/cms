package digital.sadad.project.core.config.model.plugin.security.jwt

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.AuthSchemesConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import kotlin.time.Duration

open class JWTConfig(
    val issuer: String,
    val audience: String,
    val realm: String? = null,
    val expiration: Duration? = null,
    val authHeader: String? = null,
    val authSchemes: digital.sadad.project.core.config.model.plugin.security.AuthSchemesConfig? = null,
    session: digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig? = null,
) : digital.sadad.project.core.config.model.plugin.security.AuthConfig(session)
