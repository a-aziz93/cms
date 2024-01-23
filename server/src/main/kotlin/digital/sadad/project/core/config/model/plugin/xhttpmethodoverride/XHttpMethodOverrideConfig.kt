package digital.sadad.project.core.config.model.plugin.xhttpmethodoverride

import digital.sadad.project.core.config.model.plugin.PluginConfig

class XHttpMethodOverrideConfig(
    enable: Boolean? = null,
    val headerName: String? = null,
) : PluginConfig(enable)
