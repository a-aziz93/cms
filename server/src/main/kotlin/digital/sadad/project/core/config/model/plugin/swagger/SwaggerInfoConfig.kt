package digital.sadad.project.core.config.model.plugin.swagger

data class SwaggerInfoConfig(
    val title: String? = null,
    val version: String? = null,
    val description: String? = null,
    val termsOfService: String? = null,
    val contact: SwaggerContactConfig? = null,
    val license: SwaggerLicenseConfig? = null,
)