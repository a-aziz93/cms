package digital.sadad.project.core.config.model.plugin.hsts

import digital.sadad.project.core.config.model.plugin.PluginConfig

class HSTSConfig(
    enable: Boolean? = null,
    val global: HSTSHostConfig? = null,
    val hostSpecific: Map<String, HSTSHostConfig>? = null,
) : PluginConfig(enable)