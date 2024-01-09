package core.crud.network.websocket.model

import io.ktor.websocket.*
import java.util.concurrent.atomic.*

class WSConnection(val session: DefaultWebSocketSession) {
    companion object {
        val lastId = AtomicInteger(0)
    }
    val name = "user${lastId.getAndIncrement()}"
}