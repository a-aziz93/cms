package digital.sadad.project.core.config.model.plugin.session

import digital.sadad.project.core.config.model.plugin.session.CookieConfig

data class SessionCookieConfig(
    val name: String? = null,
    val cookie: CookieConfig? = null,
)