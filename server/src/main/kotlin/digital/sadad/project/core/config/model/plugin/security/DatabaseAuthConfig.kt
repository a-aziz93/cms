package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

abstract class DatabaseAuthConfig(
    name: String? = null,
    val databases: Set<String>?,
    session: SessionCookieConfig?,
) : AuthConfig(name, session)