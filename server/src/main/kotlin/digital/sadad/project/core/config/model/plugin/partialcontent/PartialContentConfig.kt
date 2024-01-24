package digital.sadad.project.core.config.model.plugin.partialcontent

import digital.sadad.project.core.config.model.plugin.PluginConfig

class PartialContentConfig(
    enable: Boolean? = null,
    val maxRangeCount: Int? = null,
) : PluginConfig(enable)