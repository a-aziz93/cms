package digital.sadad.project.core.config.model.plugin.swagger

import digital.sadad.project.core.config.model.plugin.PluginConfig

class SwaggerConfig(
    enable: Boolean? = null,
    val forwardRoot: Boolean? = null,
    val swaggerUrl: String? = null,
    val rootHostPath: String? = null,
    val authentication: String? = null,
    val info: SwaggerInfoConfig? = null,
    val securityScheme: Map<String, SwaggerSecuritySchemeConfig>? = null,
) : PluginConfig(enable)
