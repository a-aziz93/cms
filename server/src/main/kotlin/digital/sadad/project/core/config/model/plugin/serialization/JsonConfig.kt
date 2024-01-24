package digital.sadad.project.core.config.model.plugin.serialization

import io.ktor.http.*

data class JsonConfig(
    val encodeDefaults: Boolean? = null,
    val explicitNulls: Boolean? = null,
    val ignoreUnknownKeys: Boolean? = null,
    val isLenient: Boolean? = null,
    val allowStructuredMapKeys: Boolean? = null,
    val prettyPrint: Boolean? = null,
    val prettyPrintIndent: String? = null,
    val coerceInputValues: Boolean? = null,
    val useArrayPolymorphism: Boolean? = null,
    val classDiscriminator: String? = null,
    val allowSpecialFloatingPointValues: Boolean? = null,
    val useAlternativeNames: Boolean? = null,
    val decodeEnumsCaseInsensitive: Boolean? = null,
)
