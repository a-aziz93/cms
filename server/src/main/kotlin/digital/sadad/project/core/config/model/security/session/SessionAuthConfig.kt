package digital.sadad.project.core.config.model.security.session

import digital.sadad.project.core.config.model.session.CookieConfig

data class SessionAuthConfig(
    val name: String? = null,
    val cookie: CookieConfig? = null,
    val skipIfExists: Boolean? = null,
)