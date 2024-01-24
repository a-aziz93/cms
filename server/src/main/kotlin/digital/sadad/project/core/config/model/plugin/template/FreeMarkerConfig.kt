package digital.sadad.project.core.config.model.plugin.template

import digital.sadad.project.core.config.model.plugin.PluginConfig

class FreeMarkerConfig(
    enable: Boolean? = null,
    val classPaths: List<String>? = null,
    val filePaths: List<String>? = null,
) : PluginConfig(enable)
