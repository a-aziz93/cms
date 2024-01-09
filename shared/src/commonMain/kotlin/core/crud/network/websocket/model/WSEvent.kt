package core.crud.network.websocket.model

data class WSEvent(
    val type: String,
    val data: Any?
)