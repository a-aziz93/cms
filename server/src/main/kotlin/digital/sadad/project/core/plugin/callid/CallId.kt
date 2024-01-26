package digital.sadad.project.core.plugin.callid

import digital.sadad.project.core.config.model.plugin.callid.CallIdConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*

fun Application.configureCallId(config: CallIdConfig) {
    if (config.enable == true) {
        install(CallId) {
            config.verify?.let {
                verify(it.dictionary, it.reject)
            }

            if (config.header?.let {
                    header(config.header)
                } == null) {
                config.retrieveFromHeader?.let {
                    retrieveFromHeader(config.retrieveFromHeader)
                }
                config.replyToHeader?.let {
                    replyToHeader(config.replyToHeader)
                }
            }
        }
    }
}