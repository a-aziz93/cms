package digital.sadad.project.core.config.model.plugin.security.basic

import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

open class BasicAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val charset: String? = null,
    val digestFunction: DigestFunctionConfig? = null,
    databases: Set<String>? = null,
    session: SessionCookieConfig? = null,
) : DatabaseAuthConfig(
    name,
    databases,
    session,
)