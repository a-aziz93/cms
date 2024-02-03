package digital.sadad.project.core.config.model.plugin.security.oauth

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

class OAuthConfig(
    name: String? = null,
    val server: OAuthServerConfig,
    val client: OAuthClientConfig? = null,
    session: SessionAuthConfig? = null,
) : AuthConfig(
    name,
    session,
)