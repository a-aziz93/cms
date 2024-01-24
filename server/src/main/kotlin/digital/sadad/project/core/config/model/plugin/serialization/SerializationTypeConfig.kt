package digital.sadad.project.core.config.model.plugin.serialization

import io.ktor.http.*

data class SerializationTypeConfig<T : Any>(
    val config: T? = null,
    val contentType: ContentType = ContentType.Application.Json,
)
