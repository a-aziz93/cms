package digital.sadad.project.core.config.model.serialization

import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNamingStrategy

data class SerializationJsonConfig(
    var encodeDefaults: Boolean? = null,
    var explicitNulls: Boolean? = null,
    var ignoreUnknownKeys: Boolean? = null,
    var isLenient: Boolean? = null,
    var allowStructuredMapKeys: Boolean? = null,
    var prettyPrint: Boolean? = null,
    var prettyPrintIndent: String? = null,
    var coerceInputValues: Boolean? = null,
    var useArrayPolymorphism: Boolean? = null,
    var classDiscriminator: String? = null,
    var allowSpecialFloatingPointValues: Boolean? = null,
    var useAlternativeNames: Boolean? = null,
    var decodeEnumsCaseInsensitive: Boolean? = null,
    val contentType: ContentType = ContentType.Application.Json,
)
