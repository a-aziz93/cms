package digital.sadad.project.core.config.model

import digital.sadad.project.core.config.model.cache.CacheConfig
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.plugin.applicationmonitoring.ApplicationMonitoringConfig
import digital.sadad.project.core.config.model.plugin.autoheadresponse.AutoHeadResponseConfig
import digital.sadad.project.core.config.model.plugin.resources.ResourcesConfig
import digital.sadad.project.core.config.model.plugin.cachingheaders.CachingHeadersConfig
import digital.sadad.project.core.config.model.plugin.callid.CallIdConfig
import digital.sadad.project.core.config.model.plugin.callloging.CallLoggingConfig
import digital.sadad.project.core.config.model.plugin.compression.CompressionConfig
import digital.sadad.project.core.config.model.plugin.cors.CORSConfig
import digital.sadad.project.core.config.model.plugin.dataconversion.DataConversionConfig
import digital.sadad.project.core.config.model.plugin.conditionalheaders.ConditionalHeadersConfig
import digital.sadad.project.core.config.model.plugin.defaultheaders.DefaultHeadersConfig
import digital.sadad.project.core.config.model.plugin.forwardedheaders.ForwardedHeadersConfig
import digital.sadad.project.core.config.model.plugin.graphql.GraphQLConfig
import digital.sadad.project.core.config.model.plugin.hsts.HSTSConfig
import digital.sadad.project.core.config.model.plugin.httpsredirect.HTTPSRedirectConfig
import digital.sadad.project.core.config.model.plugin.partialcontent.PartialContentConfig
import digital.sadad.project.core.config.model.plugin.ratelimit.RateLimitsConfig
import digital.sadad.project.core.config.model.plugin.di.KoinConfig
import digital.sadad.project.core.config.model.plugin.dropwizardmetrics.DropwizardMetricsConfig
import digital.sadad.project.core.config.model.plugin.routing.RoutingConfig
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.config.model.plugin.serialization.SerializationConfig
import digital.sadad.project.core.config.model.plugin.session.SessionConfig
import digital.sadad.project.core.config.model.plugin.statuspages.StatusPagesConfig
import digital.sadad.project.core.config.model.plugin.swagger.SwaggerConfig
import digital.sadad.project.core.config.model.plugin.templating.FreeMarkerConfig
import digital.sadad.project.core.config.model.plugin.locations.LocationsConfig
import digital.sadad.project.core.config.model.plugin.micrometermetrics.MicrometerMetricsConfig
import digital.sadad.project.core.config.model.plugin.validation.ValidationConfig
import digital.sadad.project.core.config.model.plugin.websockets.WebSocketsConfig
import digital.sadad.project.core.config.model.plugin.xhttpmethodoverride.XHttpMethodOverrideConfig
import digital.sadad.project.core.config.model.storage.StorageConfig

data class Config(
    val cache: Map<String, CacheConfig>? = null,
    val storage: StorageConfig? = null,
    val database: Map<String, DatabaseConfig>? = null,
    val koin: KoinConfig? = null,
    val serialization: SerializationConfig? = null,
    val httpsRedirect: HTTPSRedirectConfig? = null,
    val routing: RoutingConfig? = null,
    val websockets: WebSocketsConfig? = null,
    val graphql: GraphQLConfig? = null,
    val callLogging: CallLoggingConfig? = null,
    val callId: CallIdConfig? = null,
    val rateLimit: RateLimitsConfig? = null,
    val cors: CORSConfig? = null,
    val compression: CompressionConfig? = null,
    val partialContent: PartialContentConfig? = null,
    val dataConversion: DataConversionConfig? = null, val validation: ValidationConfig? = null,
    val resources: ResourcesConfig? = null,
    val locations: LocationsConfig? = null,
    val statusPages: StatusPagesConfig? = null,
    val defaultHeaders: DefaultHeadersConfig? = null,
    val cachingHeaders: CachingHeadersConfig? = null,
    val conditionalHeaders: ConditionalHeadersConfig? = null,
    val forwardedHeaders: ForwardedHeadersConfig? = null,
    val hsts: HSTSConfig? = null,
    val autoHeadResponse: AutoHeadResponseConfig? = null,
    val xHttpMethodOverride: XHttpMethodOverrideConfig? = null,
    val session: SessionConfig? = null,
    val security: SecurityConfig? = null,
    val freeMarker: FreeMarkerConfig? = null,
    val swagger: SwaggerConfig? = null,
    val applicationMonitoring: ApplicationMonitoringConfig? = null,
    val micrometerMetrics: MicrometerMetricsConfig? = null,
    val dropwizardMetrics: DropwizardMetricsConfig? = null,
)
