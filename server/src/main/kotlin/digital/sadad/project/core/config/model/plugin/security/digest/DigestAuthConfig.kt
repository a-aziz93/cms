package digital.sadad.project.core.config.model.plugin.security.digest

import digital.sadad.project.core.config.model.plugin.security.AuthConfig
import digital.sadad.project.core.config.model.plugin.security.DatabaseAuthConfig
import digital.sadad.project.core.config.model.plugin.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

class DigestAuthConfig(
    val realm: String? = null,
    val algorithmName: String? = null,
    databases: Set<String>? = null,
    session: SessionAuthConfig? = null,
) : DatabaseAuthConfig(databases, session)