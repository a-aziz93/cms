package digital.sadad.project.core.config.model.websocket

import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


data class WebSocketsConfig(
    val pingPeriod: Duration? = null,
    val timeout: Duration = 15.toDuration(DurationUnit.SECONDS),
    val maxFrameSize: Long = Long.MAX_VALUE,
    val masking: Boolean = false,
)
