package digital.sadad.project.core.config.model.plugin.security.form

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

class FormAuthConfig(
    name: String? = null,
    val userParamName: String? = null,
    val passwordParamName: String? = null,
    databases: Set<String>? = null,
    session: SessionAuthConfig? = null,
) : DatabaseAuthConfig(
    name,
    databases,
    session,
)