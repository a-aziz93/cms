package digital.sadad.project.core.config.model.plugin.callid

import digital.sadad.project.core.config.model.plugin.PluginConfig
import digital.sadad.project.core.plugin.callid.CallIdVerifyConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.response.*

class CallIdConfig(
    enable: Boolean? = null,
    val verify: CallIdVerifyConfig? = null,
    val header: String? = null,
    val retrieveFromHeader: String? = null,
    val replyToHeader: String? = null,
) : PluginConfig(enable)