package digital.sadad.project.core.config.model.plugin.routing

import digital.sadad.project.core.config.model.plugin.PluginConfig

class RoutingConfig(
    enable: Boolean? = null,
    val staticRootPath: String? = null,
    val staticFiles: StaticContentConfig? = null,
    val staticResources: StaticContentConfig? = null,
) : PluginConfig(enable)