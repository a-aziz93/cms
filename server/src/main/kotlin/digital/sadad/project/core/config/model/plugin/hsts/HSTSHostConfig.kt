package digital.sadad.project.core.config.model.plugin.hsts

import digital.sadad.project.core.config.model.plugin.PluginConfig

class HSTSHostConfig(
    val preload: Boolean? = null,
    val includeSubDomains: Boolean? = null,
    val maxAgeInSeconds: Long? = null,
    val customDirectives: Map<String, String?>? = null,
)