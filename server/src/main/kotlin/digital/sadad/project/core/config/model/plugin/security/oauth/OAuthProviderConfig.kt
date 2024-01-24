package digital.sadad.project.core.config.model.plugin.security.oauth

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

data class OAuthProviderConfig(
    val serverProvider: OAuthServerConfig,
    val urlProvider: OAuthURLConfig,
)