package digital.sadad.project.core.config.model.plugin.security.basic

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

open class BasicAuthConfig(
    val realm: String? = null,
    val charset: String? = null,
    session: digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig? = null,
): digital.sadad.project.core.config.model.plugin.security.AuthConfig(session)