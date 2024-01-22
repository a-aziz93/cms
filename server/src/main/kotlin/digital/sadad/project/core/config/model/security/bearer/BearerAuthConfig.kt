package digital.sadad.project.core.config.model.security.bearer

import digital.sadad.project.core.config.model.security.AuthSchemesConfig
import io.ktor.utils.io.charsets.*
import kotlin.text.Charsets

data class BearerAuthConfig(
    val realm: String? = null,
    val authHeader: String? = null,
    val authSchemes: AuthSchemesConfig? = null,
)