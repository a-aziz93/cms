package digital.sadad.project.core.config.model.plugin.serialization

import kotlinx.serialization.SerializationException

data class CBORConfig(
    val encodeDefaults: Boolean? = null,
    val ignoreUnknownKeys: Boolean? = null,
)