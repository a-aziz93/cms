package digital.sadad.project.core.config.model.plugin.security.form

import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig

class FormAuthConfig(
    name: String? = null,
    val userParamName: String? = null,
    val passwordParamName: String? = null,
    databases: Set<String>? = null,
    session: SessionCookieConfig? = null,
) : DatabaseAuthConfig(
    name,
    databases,
    session,
)