package digital.sadad.project.core.config.model.security.oauth

import digital.sadad.project.core.config.model.security.session.SessionAuthConfig

data class OAuthProviderConfig(
    val serverProvider: OAuthServerConfig,
    val urlProvider: OAuthURLConfig,
)