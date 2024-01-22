package digital.sadad.project.core.config.model.security.digest

import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

data class DigestAuthConfig(
    val realm: String? = null,
    val algorithmName: String? = null,
)