package digital.sadad.project.core.config.model

data class AuthConfig(
    val oauth: Map<String, OAuthProviderConfig>? = null,
    val jwtHS256: Map<String, JWTHS256Config>? = null,
    val jwtRS256: Map<String, JWTRS256Config>? = null,
)
