package digital.sadad.project.core.plugin.serialization

import digital.sadad.project.core.config.model.plugin.serialization.SerializationConfig
import io.ktor.serialization.kotlinx.cbor.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.serialization.kotlinx.xml.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.*
import nl.adaptivity.xmlutil.serialization.*
import kotlinx.serialization.cbor.*
import io.ktor.serialization.kotlinx.protobuf.*
import kotlinx.serialization.protobuf.*

/**
 * Configure the serialization of our application based on JSON
 */
@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization(config: SerializationConfig) {
    if (config.enable == true) {
        install(ContentNegotiation) {

            // JSON
            config.json?.let {
                json(Json {
                    it.config?.let {
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

            // XML
            config.xml?.let {
                xml(XML {
                    it.config?.let {
                        it.repairNamespaces?.let { repairNamespaces = it }
                        it.xmlDeclMode?.let { xmlDeclMode = it }
                        it.indentString?.let { indentString = it }
                        it.autoPolymorphic?.let { autoPolymorphic = it }
                    }
                }, it.contentType)
            }

            // CBOR
            config.cbor?.let {
                cbor(Cbor {
                    it.config?.let {
                        it.encodeDefaults?.let { encodeDefaults = it }
                        it.ignoreUnknownKeys?.let { ignoreUnknownKeys = it }
                    }
                }, it.contentType)
            }

            // PROTOBUF
            config.protobuf?.let {
                protobuf(ProtoBuf {
                    it.config?.let {
                        it.encodeDefaults?.let { encodeDefaults = it }
                    }
                })
            }
        }
    }
}
