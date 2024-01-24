package digital.sadad.project.core.config.model.plugin.security.session

import digital.sadad.project.core.config.model.plugin.session.CookieConfig

data class SessionAuthConfig(
    val name: String? = null,
    val cookie: digital.sadad.project.core.config.model.plugin.session.CookieConfig? = null,
    val skipIfExists: Boolean? = null,
)