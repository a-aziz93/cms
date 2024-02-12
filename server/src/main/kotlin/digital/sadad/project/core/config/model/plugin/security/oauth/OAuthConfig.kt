package digital.sadad.project.core.config.model.plugin.security.oauth

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

class OAuthConfig(
    name: String? = null,
    val server: OAuthServerConfig,
    val client: OAuthClientConfig? = null,
    session: SessionCookieConfig? = null,
) : AuthConfig(
    name,
    session,
)