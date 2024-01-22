package digital.sadad.project.core.config.model.security.form

import digital.sadad.project.core.config.model.security.session.SessionAuthConfig

data class FormAuthConfig(
    val userParamName: String? = null,
    val passwordParamName: String? = null,
    val session: SessionAuthConfig? = null,
)