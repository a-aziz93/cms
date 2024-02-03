package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig


abstract class AuthConfig(
    val name: String? = null,
    val session: SessionAuthConfig?
)