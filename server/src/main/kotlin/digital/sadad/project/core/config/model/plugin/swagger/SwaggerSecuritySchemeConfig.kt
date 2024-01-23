package digital.sadad.project.core.config.model.plugin.swagger

import io.github.smiley4.ktorswaggerui.data.AuthKeyLocation
import io.github.smiley4.ktorswaggerui.data.AuthScheme
import io.github.smiley4.ktorswaggerui.data.AuthType

data class SwaggerSecuritySchemeConfig(
    val type: AuthType? = null,
    val name: String? = null,
    val location: AuthKeyLocation? = null,
    val scheme: AuthScheme? = null,
    val bearerFormat: String? = null,
    val openIdConnectUrl: String? = null,
    val description: String? = null,
)
