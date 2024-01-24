package digital.sadad.project.core.config.model.plugin.serialization

import digital.sadad.project.core.config.model.plugin.PluginConfig

class SerializationConfig(
    enable: Boolean? = null,
    val json: SerializationTypeConfig<JsonConfig>? = null,
    val xml: SerializationTypeConfig<XMLConfig>? = null,
    val cbor: SerializationTypeConfig<CBORConfig>? = null,
    val protobuf: SerializationTypeConfig<ProtobufConfig>? = null,
) : PluginConfig(enable)