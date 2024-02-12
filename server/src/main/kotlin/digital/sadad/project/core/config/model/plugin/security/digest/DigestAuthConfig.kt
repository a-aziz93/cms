package digital.sadad.project.core.config.model.plugin.security.digest

import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

class DigestAuthConfig(
    name: String? = null,
    val realm: String? = null,
    val algorithmName: String? = null,
    databases: Set<String>? = null,
    session: SessionCookieConfig? = null,
) : DatabaseAuthConfig(
    name,
    databases,
    session,
)