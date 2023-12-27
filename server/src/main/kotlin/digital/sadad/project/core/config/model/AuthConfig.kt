package digital.sadad.project.core.config.model

data class AuthConfig(
    val oauth: Map<String, OAuthProviderConfig>? = null,
    val jwt: JWTConfig? = null,
)
