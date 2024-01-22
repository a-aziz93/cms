package digital.sadad.project.core.config.model.security.oauth

data class OAuthProviderConfig(
    val serverProvider: OAuthServerConfig,
    val urlProvider: OAuthURLConfig,
)