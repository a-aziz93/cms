package digital.sadad.project.core.config.model.security.oauth

import digital.sadad.project.core.config.model.security.session.SessionAuthConfig

data class OAuthConfig(
    val provider: OAuthProviderConfig,
    val client: OAuthClientConfig? = null,
    val session: SessionAuthConfig? = null,
)