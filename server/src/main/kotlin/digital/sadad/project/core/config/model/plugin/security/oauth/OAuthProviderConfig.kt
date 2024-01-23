package digital.sadad.project.core.config.model.plugin.security.oauth

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

data class OAuthProviderConfig(
    val serverProvider: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthServerConfig,
    val urlProvider: digital.sadad.project.core.config.model.plugin.security.oauth.OAuthURLConfig,
)