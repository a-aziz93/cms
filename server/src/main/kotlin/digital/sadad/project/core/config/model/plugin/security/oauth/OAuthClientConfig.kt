package digital.sadad.project.core.config.model.plugin.security.oauth

data class OAuthClientConfig(
    val grantType: String,
    val serverUrl: String,
    val realm: String,
    val clientId: String,
    val clientSecret: String,
    val username: String? = null,
    val password: String? = null,
)
