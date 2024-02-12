package digital.sadad.project.core.config.model.plugin.security.bearer

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.AuthSchemesConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

class BearerAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
    session: SessionCookieConfig? = null,
) : AuthConfig(
    name,
    session,
)