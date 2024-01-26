package digital.sadad.project.core.config.model.plugin.shutdown

import digital.sadad.project.core.config.model.plugin.PluginConfig

class ShutDownConfig(
    enable: Boolean? = null,
    val shutDownUrl: String? = null,
    val exitCodeSupplier: Int? = null,
) : PluginConfig(enable)
