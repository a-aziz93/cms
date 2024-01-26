package digital.sadad.project.core.config.model.plugin.conditionalheaders

import digital.sadad.project.core.config.model.plugin.PluginConfig

class ConditionalHeadersConfig(
    enable: Boolean? = null,
    val versionHeadersPath: String? = null,
) : PluginConfig(enable)