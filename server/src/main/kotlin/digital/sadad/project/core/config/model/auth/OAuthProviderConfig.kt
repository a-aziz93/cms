package digital.sadad.project.core.config.model.auth

data class OAuthProviderConfig(
    val serverProvider: OAuthServerConfig,
    val urlProvider: OAuthURLConfig,
)