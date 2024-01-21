package digital.sadad.project.core.config.model.security

data class AuthConfig(
    val basic: Map<String, BasicAuthConfig>? = null,
    val digest: Map<String, DigestAuthConfig>? = null,
    val bearer: Map<String, BearerAuthConfig>? = null,
    val form: Map<String, FormAuthConfig>? = null,
    val session: Map<String, SessionAuthConfig>? = null,
    val ldap: Map<String, LDAPAuthConfig>? = null,
    val jwtHS256: Map<String, JWTHS256Config>? = null,
    val jwtRS256: Map<String, JWTRS256Config>? = null,
    val oauth: Map<String, OAuthProviderConfig>? = null,
)
