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
    val webSocketConfig = appConfig.config.websockets

    install(WebSockets) {
        pingPeriod = webSocketConfig?.pingPeriod?.let { Duration.ofSeconds(it.inWholeSeconds) }
        timeout = Duration.ofSeconds(webSocketConfig?.timeout?.inWholeSeconds ?: 15)
        maxFrameSize = webSocketConfig?.maxFrameSize ?: Long.MAX_VALUE
        masking = webSocketConfig?.masking ?: false
        // Configure WebSockets
        // Serializer for WebSockets
        contentConverter = KotlinxWebsocketSerializationConverter(Json {
            prettyPrint = true
            isLenient = true
        })
        // Remember it will close the connection if you don't send a ping in pingPeriod seconds
        // https://ktor.io/docs/websocket.html#configure
    }

    routing {
        get("/websocket") {
            call.respond(
                FreeMarkerContent(
                    "websocket/index.ftl",
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