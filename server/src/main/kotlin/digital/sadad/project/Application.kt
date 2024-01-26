package digital.sadad.project

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.plugin.applicationmonitoring.configureApplicationMonitoring
import digital.sadad.project.core.plugin.authheadresponse.configureAutoHeadResponse
import digital.sadad.project.core.plugin.cachingheaders.configureCachingHeaders
import digital.sadad.project.core.plugin.callid.configureCallId
import digital.sadad.project.core.plugin.calllogging.configureCallLogging
import digital.sadad.project.core.plugin.conditionalheaders.configureConditionalHeaders
import digital.sadad.project.core.plugin.compression.configureCompression
import digital.sadad.project.core.plugin.cors.configureCors
import digital.sadad.project.core.plugin.dataconversion.configureDataConversion
import digital.sadad.project.core.plugin.defaultheaders.configureDefaultHeaders
import digital.sadad.project.core.plugin.di.configureKoin
import digital.sadad.project.core.plugin.dropwizardmetrics.configureDropwizardMetrics
import digital.sadad.project.core.plugin.forwardedheaders.configureForwardedHeaders
import digital.sadad.project.core.plugin.graphql.configureGraphQL
import digital.sadad.project.core.plugin.hsts.configureHSTS
import digital.sadad.project.core.plugin.httpsredirect.configureHttpsRedirect
import digital.sadad.project.core.plugin.partialcontent.configurePartialContent
import digital.sadad.project.core.plugin.locations.configureLocations
import digital.sadad.project.core.plugin.micrometermetrics.configureMicrometerMetrics
import digital.sadad.project.core.plugin.ratelimit.configureRateLimit
import digital.sadad.project.core.plugin.resources.configureResources
import digital.sadad.project.core.plugin.routing.configureRouting
import digital.sadad.project.core.plugin.security.configureSecurity
import digital.sadad.project.core.plugin.serialization.configureSerialization
import digital.sadad.project.core.plugin.session.configureSession
import digital.sadad.project.core.plugin.shutdown.configureShutdown
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

    // Configure the Serialization plugin
    appConfig.config.serialization?.let { configureSerialization(it) }

    // Configure the HttpsRedirect plugin
    appConfig.config.httpsRedirect?.let { configureHttpsRedirect(it, appConfig.sslPort) }

    // Configure the Routing plugin
    appConfig.config.routing?.let { configureRouting(it) }

    // Configure the Websockets plugin
    appConfig.config.websockets?.let {
        configureWebSockets(
            it,
            if (appConfig.sslPort == null) "ws://${appConfig.baseConfig.host}:${appConfig.baseConfig.port}" else "wss://${appConfig.baseConfig.host}:${appConfig.sslPort}"
        )
    }

    // Configure the Graphql plugin
    appConfig.config.graphql?.let { configureGraphQL(it) }

    // Configure the CallLogging plugin
    appConfig.config.callLogging?.let { configureCallLogging(it) }

    // Configure the CallLogging plugin
    appConfig.config.callId?.let { configureCallId(it) }

    // Configure the RateLimit plugin
    appConfig.config.rateLimit?.let { configureRateLimit(it) }

    // Configure the CORS plugin
    appConfig.config.cors?.let { configureCors(it) }

    // Configure the compression plugin
    appConfig.config.compression?.let { configureCompression(it) }

    // Configure the PartialContent plugin
    appConfig.config.partialContent?.let { configurePartialContent(it) }

    // Configure the HttpsRedirect plugin
    appConfig.config.dataConversion?.let { configureDataConversion(it) }

    // Configure the validation plugin
    appConfig.config.validation?.let { configureValidation(it) }

    // Configure the Resources plugin
    appConfig.config.resources?.let { configureResources(it) }

    // Configure the Locations plugin
    appConfig.config.locations?.let { configureLocations(it) }

    // Configure the status pages plugin
    appConfig.config.statusPages?.let { configureStatusPages(it) }

    // Configure the DefaultHeaders plugin
    appConfig.config.defaultHeaders?.let { configureDefaultHeaders(it) }

    // Configure the CachingHeaders plugin
    appConfig.config.cachingHeaders?.let { configureCachingHeaders(it) }

    // Configure the ConditionalHeaders plugin
    appConfig.config.conditionalHeaders?.let { configureConditionalHeaders(it) }

    // Configure the ForwardedHeaders plugin
    appConfig.config.forwardedHeaders?.let { configureForwardedHeaders(it) }

    // Configure the HSTS plugin
    appConfig.config.hsts?.let { configureHSTS(it) }

    // Configure the AutoHeadResponse plugin
    appConfig.config.autoHeadResponse?.let { configureAutoHeadResponse(it) }

    // Configure the XHttpMethodOverride plugin
    appConfig.config.xHttpMethodOverride?.let { configureXHttpMethodOverride(it) }

    // Configure session with cookies
    appConfig.config.session?.let { configureSession(it, appConfig.config.security) }

    // Configure the security plugin with JWT
    appConfig.config.security?.let {
        configureSecurity(
            it,
            if (appConfig.sslPort == null) "http://${appConfig.baseConfig.host}:${appConfig.baseConfig.port}" else "https://${appConfig.baseConfig.host}:${appConfig.sslPort}"
        )
    }

    // Configure the FreeMarker plugin for templating .ftl files
    appConfig.config.freeMarker?.let { configureFreeMarker(it) }

    // Configure the Swagger plugin
    appConfig.config.swagger?.let { configureSwagger(it) }

    // Configure the Application monitoring plugin
    appConfig.config.applicationMonitoring?.let { configureApplicationMonitoring(it) }

    // Configure the MicrometerMetrics plugin
    appConfig.config.micrometerMetrics?.let { configureMicrometerMetrics(it) }

    // Configure the DropwizardMetrics plugin
    appConfig.config.dropwizardMetrics?.let { configureDropwizardMetrics(it) }

    // Configure the Shutdown plugin
    appConfig.config.shutdown?.let { configureShutdown(it) }
}
