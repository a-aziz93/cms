package digital.sadad.project

import digital.sadad.project.core.plugins.*
import digital.sadad.project.core.plugins.applicationmonitoring.configureApplicationMonitoring
import digital.sadad.project.core.plugins.authheadresponse.configureAutoHeadResponse
import digital.sadad.project.core.plugins.compression.configureCompression
import digital.sadad.project.core.plugins.cors.configureCors
import digital.sadad.project.core.plugins.di.configureKoin
import digital.sadad.project.core.plugins.graphql.configureGraphQL
import digital.sadad.project.core.plugins.locations.configureLocations
import digital.sadad.project.core.plugins.ratelimit.configureRateLimit
import digital.sadad.project.core.plugins.resources.configureResources
import digital.sadad.project.core.plugins.routing.configureRouting
import digital.sadad.project.core.plugins.security.configureSecurity
import digital.sadad.project.core.plugins.serialization.configureSerialization
import digital.sadad.project.core.plugins.session.configureSession
import digital.sadad.project.core.plugins.statuspages.configureStatusPages
import digital.sadad.project.core.plugins.swagger.configureSwagger
import digital.sadad.project.core.plugins.template.configureFreeMarker
import digital.sadad.project.core.plugins.validation.configureValidation
import digital.sadad.project.core.plugins.websocket.configureWebSockets
import digital.sadad.project.core.plugins.xhttpmethodoverride.configureXHttpMethodOverride
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
