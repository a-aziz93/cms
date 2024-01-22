package digital.sadad.project.core.config.model.security.basic

import digital.sadad.project.core.config.model.security.session.SessionAuthConfig

open class BasicAuthConfig(
    val realm: String? = null,
    val charset: String? = null,
    val session: SessionAuthConfig? = null,
)