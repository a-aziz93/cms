package digital.sadad.project.core.config.model.plugin.serialization

import digital.sadad.project.core.config.model.json.JsonConfig
import io.ktor.http.*

data class SerializationJsonConfig(
    val json: JsonConfig? = null,
    val contentType: ContentType = ContentType.Application.Json,
)
