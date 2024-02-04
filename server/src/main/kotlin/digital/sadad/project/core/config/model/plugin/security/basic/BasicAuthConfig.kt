package digital.sadad.project.core.config.model.plugin.security.basic

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

open class BasicAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val charset: String? = null,
    val digestFunction: DigestFunctionConfig? = null,
    databases: Set<String>? = null,
    session: SessionAuthConfig? = null,
) : DatabaseAuthConfig(
    name,
    databases,
    session,
)