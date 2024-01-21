package digital.sadad.project.core.config.model.security

import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

data class BearerAuthConfig(
    val realm: String? = null,
    val charset: String? = null,
)