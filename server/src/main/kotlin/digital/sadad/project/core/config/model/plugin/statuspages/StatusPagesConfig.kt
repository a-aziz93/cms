package digital.sadad.project.core.config.model.plugin.statuspages

import digital.sadad.project.core.config.model.plugin.PluginConfig

class StatusPagesConfig(
    enable: Boolean? = null,
    val status: List<StatusConfig>? = null,
    val statusFile: List<StatusFileConfig>? = null,
) : PluginConfig(enable)
