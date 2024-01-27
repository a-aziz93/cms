package digital.sadad.project.core.crud.network.websocket

import core.crud.service.CRUDService
import core.network.websocket.model.WSConnection
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.*
import kotlin.collections.LinkedHashSet

inline fun <reified T : Any> Application.crudWebsocket(path: String, crudService: CRUDService<T>) {
    routing {
        val connections = Collections.synchronizedSet<WSConnection?>(LinkedHashSet())
        webSocket(path) {
            val thisConnection = WSConnection(this)
            connections += thisConnection
            try {
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("Removing $thisConnection!")
                connections -= thisConnection
            }
        }
    }
}