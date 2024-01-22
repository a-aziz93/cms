package digital.sadad.project.core.config.model.security.digest

import digital.sadad.project.core.config.model.security.session.SessionAuthConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

data class DigestAuthConfig(
    val realm: String? = null,
    val algorithmName: String? = null,
    val session: SessionAuthConfig? = null,
)