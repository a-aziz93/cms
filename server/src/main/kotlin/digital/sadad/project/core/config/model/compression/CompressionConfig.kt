package digital.sadad.project.core.config.model.compression

data class CompressionConfig(
    val gzip: CompressionEncoderConfig? = null,
    val deflate: CompressionEncoderConfig? = null,
    val identity: CompressionEncoderConfig? = null,
)
