package digital.sadad.project.core.config.model.plugin.serialization

import digital.sadad.project.core.config.model.json.JsonConfig
import digital.sadad.project.core.config.model.plugin.PluginConfig

class SerializationConfig(
    enable: Boolean? = null,
    val json: SerializationJsonConfig? = null,
) : PluginConfig(enable)