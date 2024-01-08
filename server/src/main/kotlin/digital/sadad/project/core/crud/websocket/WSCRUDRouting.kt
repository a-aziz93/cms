package digital.sadad.project.core.crud.websocket

import io.ktor.server.routing.*
import io.ktor.server.websocket.*

fun Routing.crudRoute(path: String) {
    webSocket(path) {

    }
}