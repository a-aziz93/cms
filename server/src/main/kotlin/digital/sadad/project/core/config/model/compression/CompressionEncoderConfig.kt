package digital.sadad.project.core.config.model.compression

import io.ktor.http.*

data class CompressionEncoderConfig(
    val priority: Double? = null,
    val minimumSize: Long? = null,
    val matchContentType: List<ContentType>? = null,
    val excludeContentType: List<ContentType>? = null,
)


