package digital.sadad.project.core.plugins.serialization

import digital.sadad.project.core.config.AppConfig
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

/**
 * Configure the serialization of our application based on JSON
 */
fun Application.configureSerialization() {
    val appConfig: AppConfig by inject()
    appConfig.config.serialization?.let { serialization ->
        install(ContentNegotiation) {
            serialization.json?.let {
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
                }, serialization.json.contentType)
            }
        }
    }
}
