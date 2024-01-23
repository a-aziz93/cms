package digital.sadad.project.core.config.model.plugin.compression

import digital.sadad.project.core.config.model.plugin.PluginConfig

class CompressionConfig(
    enable: Boolean? = null,
    val gzip: CompressionEncoderConfig? = null,
    val deflate: CompressionEncoderConfig? = null,
    val identity: CompressionEncoderConfig? = null,
) : PluginConfig(enable)
