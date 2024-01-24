package digital.sadad.project

import digital.sadad.project.core.config.AppConfig
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
import digital.sadad.project.core.plugin.websockets.configureWebSockets
import digital.sadad.project.core.plugin.xhttpmethodoverride.configureXHttpMethodOverride
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.inject


fun main(args: Array<String>): Unit = EngineMain.main(args)

/**
 * Configure our application with the plugins
 */
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val appConfig: AppConfig by inject()
    configureKoin(appConfig) // Configure the Koin plugin to inject dependencies
    configureSerialization(appConfig.config.serialization) // Configure the serialization plugin
    appConfig.config.routing?.let { configureRouting(it)} // Configure the routing plugin
    appConfig.config.websockets?.let { configureWebSockets(it) } // Configure the websockets plugin
    appConfig.config.graphql?.let { configureGraphQL(it) } // Configure the graphql plugin
    appConfig.config.rateLimit?.let { configureRateLimit(it) } // Configure the RateLimit plugin
    appConfig.config.cors?.let { configureCors(it) } // Configure the CORS plugin
    appConfig.config.compression?.let { configureCompression(it) } // Configure the compression plugin
    appConfig.config.validation?.let { configureValidation(it) } // Configure the validation plugin
    appConfig.config.resources?.let { configureResources(it) } // Configure the Resources plugin
    appConfig.config.locations?.let { configureLocations(it) } // Configure the Locations plugin
    appConfig.config.statusPages?.let { configureStatusPages(it) } // Configure the status pages plugin
    appConfig.config.autoHeadResponse?.let { configureAutoHeadResponse(it) } // Configure the AutoHeadResponse plugin
    appConfig.config.xHttpMethodOverride?.let { configureXHttpMethodOverride(it) } // Configure the XHttpMethodOverride plugin
    appConfig.config.session?.let { configureSession(it) } // Configure session with cookies
    appConfig.config.security?.let { configureSecurity(it) }// Configure the security plugin with JWT
    appConfig.config.freeMarker?.let { configureFreeMarker(it) } // Configure the FreeMarker plugin for templating .ftl files
    appConfig.config.swagger?.let { configureSwagger(it) } // Configure the Swagger plugin
    appConfig.config.applicationMonitoring?.let { configureApplicationMonitoring(it) } // Configure the Application monitoring plugin for templating .ftl files
}
