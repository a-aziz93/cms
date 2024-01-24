package digital.sadad.project.core.plugin.websockets

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.plugin.websockets.WebSocketsConfig
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.time.Duration

fun Application.configureWebSockets(config: WebSocketsConfig) {
    if (config.enable == true) {
        install(WebSockets) {
            config.pingPeriod?.let { pingPeriod = Duration.ofSeconds(it.inWholeSeconds) }
            config.timeout?.inWholeSeconds?.let { timeout = Duration.ofSeconds(it) }
            config.maxFrameSize?.let { maxFrameSize = it }
            config.masking?.let { masking = it }
            // Configure WebSockets
            // Serializer for WebSockets
            config.contentConverter?.let {
                contentConverter = KotlinxWebsocketSerializationConverter(Json {
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
                })
            }
            // Remember it will close the connection if you don't send a ping in pingPeriod seconds
            // https://ktor.io/docs/websocket.html#configure
        }

        config.page?.let { page ->
            routing {
                get(page.uri ?: "/websocket") {
                    call.respond(
                        FreeMarkerContent(
                            page.filePath ?: "websocket/index.ftl",
                            mapOf(
                                "baseAddress" to "${
                                    if (appConfig.baseConfig.keys().contains("ktor.security.ssl")) "wss" else "ws"
                                }://localhost:${
                                    appConfig.baseConfig.propertyOrNull("ktor.deployment.sslPort")?.getString()
                                        ?.toInt() ?: appConfig.baseConfig.port
                                }"
                            )
                        )
                    )
                }
            }
        }
    }
}