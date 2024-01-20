package digital.sadad.project.core.config.model.security

data class OAuthProviderConfig(
    val serverProvider: OAuthServerConfig,
    val urlProvider: OAuthURLConfig,
)