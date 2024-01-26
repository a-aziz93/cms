package digital.sadad.project.core.config.model.plugin.security.form

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig

class FormAuthConfig(
    val userParamName: String? = null,
    val passwordParamName: String? = null,
    session: SessionAuthConfig? = null,
) : AuthConfig(session)