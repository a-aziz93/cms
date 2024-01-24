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
import digital.sadad.project.core.plugin.templating.configureFreeMarker
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

    // Configure the Koin plugin to inject dependencies
    configureKoin(
        appConfig.config.koin,
        appConfig.config.database,
        appConfig.config.security
    )

    // Configure the serialization plugin
    appConfig.config.serialization?.let { configureSerialization(it) }

    // Configure the routing plugin
    appConfig.config.routing?.let { configureRouting(it) }

    // Configure the websockets plugin
    appConfig.config.websockets?.let {
        configureWebSockets(
            it,
            appConfig.baseConfig.keys().contains("ktor.security.ssl"),
            appConfig.baseConfig.propertyOrNull("ktor.deployment.sslPort")?.getString()
                ?.toInt() ?: appConfig.baseConfig.port
        )
    }

    // Configure the graphql plugin
    appConfig.config.graphql?.let { configureGraphQL(it) }

    // Configure the RateLimit plugin
    appConfig.config.rateLimit?.let { configureRateLimit(it) }

    // Configure the CORS plugin
    appConfig.config.cors?.let { configureCors(it) }

    // Configure the compression plugin
    appConfig.config.compression?.let { configureCompression(it) }

    // Configure the validation plugin
    appConfig.config.validation?.let { configureValidation(it) }

    // Configure the Resources plugin
    appConfig.config.resources?.let { configureResources(it) }

    // Configure the Locations plugin
    appConfig.config.locations?.let { configureLocations(it) }

    // Configure the status pages plugin
    appConfig.config.statusPages?.let { configureStatusPages(it) }

    // Configure the AutoHeadResponse plugin
    appConfig.config.autoHeadResponse?.let { configureAutoHeadResponse(it) }

    // Configure the XHttpMethodOverride plugin
    appConfig.config.xHttpMethodOverride?.let { configureXHttpMethodOverride(it) }

    // Configure session with cookies
    appConfig.config.session?.let { configureSession(it, appConfig.config.security) }

    // Configure the security plugin with JWT
    appConfig.config.security?.let { configureSecurity(it) }

    // Configure the FreeMarker plugin for templating .ftl files
    appConfig.config.freeMarker?.let { configureFreeMarker(it) }

    // Configure the Swagger plugin
    appConfig.config.swagger?.let { configureSwagger(it) }

    // Configure the Application monitoring plugin for templating .ftl files
    appConfig.config.applicationMonitoring?.let { configureApplicationMonitoring(it) }
}
