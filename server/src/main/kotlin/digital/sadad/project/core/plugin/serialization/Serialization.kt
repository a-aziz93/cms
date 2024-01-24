package digital.sadad.project.core.plugin.serialization

import digital.sadad.project.core.config.model.plugin.serialization.SerializationConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

/**
 * Configure the serialization of our application based on JSON
 */
@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization(config: SerializationConfig?) {
    install(ContentNegotiation) {
        config?.let {
            it.json?.let {
                json(Json {
                    it.json?.let {
                        it.encodeDefaults?.let { encodeDefaults = it }
                        it.explicitNulls?.let { explicitNulls = it }
                        it.ignoreUnknownKeys?.let { ignoreUnknownKeys = it }
                        it.isLenient?.let { isLenient = it }
                        it.allowStructuredMapKeys?.let { allowStructuredMapKeys = it }
                        it.prettyPrint?.let { prettyPrint = it }
                        it.prettyPrintIndent?.let { prettyPrintIndent = it }
                        it.coerceInputValues?.let { coerceInputValues = it }
                        it.useArrayPolymorphism?.let { useArrayPolymorphism = it }
                        it.classDiscriminator?.let { classDiscriminator = it }
                        it.allowSpecialFloatingPointValues?.let { allowSpecialFloatingPointValues = it }
                        it.useAlternativeNames?.let { useAlternativeNames = it }
                        it.decodeEnumsCaseInsensitive?.let { decodeEnumsCaseInsensitive = it }
                    }
                }, it.contentType)
            }
        }
    }
}
