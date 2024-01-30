package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

abstract class DatabaseAuthConfig(
    val databases: Set<String>?,
    session: SessionAuthConfig?,
) : AuthConfig(session)