package digital.sadad.project.core.config.model.websocket

import digital.sadad.project.core.config.model.json.JsonConfig
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


data class WebSocketsConfig(
    val pingPeriod: Duration? = null,
    val timeout: Duration? = null,
    val maxFrameSize: Long? = null,
    val masking: Boolean? = null,
    val contentConverter: JsonConfig? = null,
)
