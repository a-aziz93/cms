package digital.sadad.project

import digital.sadad.project.core.plugin.applicationmonitoring.configureApplicationMonitoring
import digital.sadad.project.core.plugin.authheadresponse.configureAutoHeadResponse
import digital.sadad.project.core.plugin.compression.configureCompression
import digital.sadad.project.core.plugin.cors.configureCors
import digital.sadad.project.core.plugin.di.configureKoin
import digital.sadad.project.core.plugin.graphql.configureGraphQL
import digital.sadad.project.core.plugin.locations.configureLocations
import digital.sadad.project.core.plugin.ratelimit.configureRateLimit
import digital.sadad.project.core.plugin.resources.configureResources
import digital.sadad.project.core.plugin.routing.configureRouting
import digital.sadad.project.core.plugin.security.configureSecurity
import digital.sadad.project.core.plugin.serialization.configureSerialization
import digital.sadad.project.core.plugin.session.configureSession
import digital.sadad.project.core.plugin.statuspages.configureStatusPages
import digital.sadad.project.core.plugin.swagger.configureSwagger
import digital.sadad.project.core.plugin.template.configureFreeMarker
import digital.sadad.project.core.plugin.validation.configureValidation
import digital.sadad.project.core.plugin.websocket.configureWebSockets
import digital.sadad.project.core.plugin.xhttpmethodoverride.configureXHttpMethodOverride
import io.ktor.server.application.*
import io.ktor.server.netty.*


fun main(args: Array<String>): Unit = EngineMain.main(args)

/**
 * Configure our application with the plugins
 */
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureApplicationMonitoring() // Configure the Application monitoring plugin for templating .ftl files
    configureFreeMarker() // Configure the FreeMarker plugin for templating .ftl files
    configureKoin() // Configure the Koin plugin to inject dependencies
    configureSecurity() // Configure the security plugin with JWT
    configureSession() // Configure session with cookies
    configureWebSockets() // Configure the websockets plugin
    configureGraphQL() // Configure the graphql plugin
    configureSerialization() // Configure the serialization plugin
    configureRouting() // Configure the routing plugin
    configureValidation() // Configure the validation plugin
    configureStatusPages() // Configure the status pages plugin
    configureCompression() // Configure the compression plugin
    configureCors() // Configure the CORS plugin
    configureRateLimit() // Configure the RateLimit plugin
    configureSwagger() // Configure the Swagger plugin
    configureResources() // Configure the Resources plugin
    configureLocations() // Configure the Locations plugin
    configureAutoHeadResponse() // Configure the AutoHeadResponse plugin
    configureXHttpMethodOverride() // Configure the XHttpMethodOverride plugin
}
