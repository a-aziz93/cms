package digital.sadad.project.core.config.model.plugin.security

import digital.sadad.project.core.config.model.plugin.session.SessionCookieConfig


abstract class AuthConfig(
    val name: String? = null,
    val session: SessionCookieConfig? = null,
)