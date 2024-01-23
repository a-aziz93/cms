package digital.sadad.project.core.config.model.plugin.security.oauth

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

class OAuthConfig(
    val provider: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthProviderConfig,
    val client: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthClientConfig? = null,
    session: digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig? = null,
) : digital.sadad.project.core.config.model.plugin.security.AuthConfig(session)