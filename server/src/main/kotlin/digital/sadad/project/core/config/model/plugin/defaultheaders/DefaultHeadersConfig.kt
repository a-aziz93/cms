package digital.sadad.project.core.config.model.plugin.defaultheaders

import digital.sadad.project.core.config.model.plugin.PluginConfig

class DefaultHeadersConfig(
    enable: Boolean? = null,
    val headers: Map<String, String>? = null,
) : PluginConfig(enable)