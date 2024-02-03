package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

abstract class DatabaseAuthConfig(
    name: String? = null,
    val databases: Set<String>?,
    session: SessionAuthConfig?,
) : AuthConfig(name, session)