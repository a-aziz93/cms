package digital.sadad.project.core.config.model.plugin.security.digest

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class DigestAuthConfig(
    val realm: String? = null,
    val algorithmName: String? = null,
    session: digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig? = null,
): digital.sadad.project.core.config.model.plugin.security.AuthConfig(session)