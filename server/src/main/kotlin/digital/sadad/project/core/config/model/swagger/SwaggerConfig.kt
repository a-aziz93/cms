package digital.sadad.project.core.config.model.swagger

data class SwaggerConfig(
    val forwardRoot: Boolean? = null,
    val swaggerUrl: String? = null,
    val rootHostPath: String? = null,
    val authentication: String? = null,
    val info: SwaggerInfoConfig? = null,
    val securityScheme: Map<String, SwaggerSecuritySchemeConfig>? = null,
)
