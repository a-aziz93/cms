package digital.sadad.project.core.plugin.forwardedheaders

import digital.sadad.project.core.config.model.plugin.forwardedheaders.ForwardedHeadersConfig
import io.ktor.server.application.*
import io.ktor.server.plugins.forwardedheaders.*

fun Application.configureForwardedHeaders(config: ForwardedHeadersConfig) {
    if (config.enable == true) {
        // WARNING: for security, do not include this if not behind a reverse proxy
        install(ForwardedHeaders) {
            config.useFirst?.let {
                if (it) {
                    useFirstValue()
                }
            }

            config.useLast?.let {
                if (it) {
                    useLastValue()
                }
            }

            config.skipLastProxies?.let { skipLastProxies(it) }

            config.skipKnownProxies?.let { skipKnownProxies(it) }
        }

        // WARNING: for security, do not include this if not behind a reverse proxy
        install(XForwardedHeaders) {

            config.xForwardedHostHeaders?.let { hostHeaders + it }
            config.xForwardedProtoHeaders?.let { protoHeaders + it }
            config.xForwardedForHeaders?.let { forHeaders + it }
            config.xForwardedHttpsFlagHeaders?.let { httpsFlagHeaders + it }
            config.xForwardedPortHeaders?.let { portHeaders + it }

            config.useFirst?.let {
                if (it) {
                    useFirstProxy()
                }
            }

            config.useFirst?.let {
                if (it) {
                    useLastProxy()
                }
            }

            config.skipLastProxies?.let { skipLastProxies(it) }

            config.skipKnownProxies?.let { skipKnownProxies(it) }

        }

    }
}