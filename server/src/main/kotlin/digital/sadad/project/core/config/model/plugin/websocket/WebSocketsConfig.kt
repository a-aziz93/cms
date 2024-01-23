package digital.sadad.project.core.config.model.plugin.websocket

import digital.sadad.project.core.config.model.json.JsonConfig
import digital.sadad.project.core.config.model.plugin.PluginConfig
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class WebSocketsConfig(
    enable: Boolean? = null,
    val pingPeriod: Duration? = null,
    val timeout: Duration? = null,
    val maxFrameSize: Long? = null,
    val masking: Boolean? = null,
    val contentConverter: JsonConfig? = null,
    val page: WebSocketPageConfig? = null,
) : PluginConfig(enable)
