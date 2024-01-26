package digital.sadad.project.core.config.model.plugin.callloging

import digital.sadad.project.core.config.model.log.LogConfig
import digital.sadad.project.core.config.model.plugin.PluginConfig

class CallLoggingConfig(
    enable: Boolean? = null,
    val logging: LogConfig? = null,
    val disableDefaultColors: Boolean? = null,
    val disableForStaticContent: Boolean? = null,
) : PluginConfig(enable)