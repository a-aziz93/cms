package digital.sadad.project.core.plugins

import digital.sadad.project.core.config.AppConfig
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import java.time.Duration

fun Application.configureWebSockets() {
    val appConfig: AppConfig by inject()
    appConfig.config.websocket?.let {
        install(WebSockets) {
            it.pingPeriod?.let { pingPeriod = Duration.ofSeconds(it.inWholeSeconds) }
            it.timeout?.inWholeSeconds?.let { timeout = Duration.ofSeconds(it) }
            it.maxFrameSize?.let { maxFrameSize = it }
            it.masking?.let { masking = it }
            // Configure WebSockets
            // Serializer for WebSockets
            it.contentConverter?.let {
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

        it.page?.let { page ->
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