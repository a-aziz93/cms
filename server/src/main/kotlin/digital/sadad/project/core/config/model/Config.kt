package digital.sadad.project.core.config.model

import digital.sadad.project.core.config.model.plugin.template.FreeMarkerConfig
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.config.model.cache.CacheConfig
import digital.sadad.project.core.config.model.plugin.compression.CompressionConfig
import digital.sadad.project.core.config.model.plugin.cors.CORSConfig
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.plugin.applicationmonitoring.ApplicationMonitoringConfig
import digital.sadad.project.core.config.model.plugin.autoheadresponse.AutoHeadResponseConfig
import digital.sadad.project.core.config.model.plugin.autoheadresponse.ResourcesConfig
import digital.sadad.project.core.config.model.plugin.ratelimit.RateLimitsConfig
import digital.sadad.project.core.config.model.plugin.serialization.SerializationConfig
import digital.sadad.project.core.config.model.plugin.session.SessionConfig
import digital.sadad.project.core.config.model.plugin.statuspages.StatusPagesConfig
import digital.sadad.project.core.config.model.storage.StorageConfig
import digital.sadad.project.core.config.model.plugin.swagger.SwaggerConfig
import digital.sadad.project.core.config.model.plugin.graphql.GraphQLConfig
import digital.sadad.project.core.config.model.plugin.routing.KoinConfig
import digital.sadad.project.core.config.model.plugin.routing.RoutingConfig
import digital.sadad.project.core.config.model.plugin.validation.LocationsConfig
import digital.sadad.project.core.config.model.plugin.validation.ValidationConfig
import digital.sadad.project.core.config.model.plugin.websockets.WebSocketsConfig
import digital.sadad.project.core.config.model.plugin.xhttpmethodoverride.XHttpMethodOverrideConfig

data class Config(
    val cache: Map<String, CacheConfig>? = null,
    val storage: StorageConfig? = null,
    val database: Map<String, DatabaseConfig>? = null,
    val koin: KoinConfig? = null,
    val serialization: SerializationConfig? = null,
    val routing: RoutingConfig? = null,
    val websockets: WebSocketsConfig? = null,
    val graphql: GraphQLConfig? = null,
    val rateLimit: RateLimitsConfig? = null,
    val cors: CORSConfig? = null,
    val compression: CompressionConfig? = null,
    val validation: ValidationConfig? = null,
    val resources: ResourcesConfig? = null,
    val locations: LocationsConfig? = null,
    val statusPages: StatusPagesConfig? = null,
    val autoHeadResponse: AutoHeadResponseConfig? = null,
    val xHttpMethodOverride: XHttpMethodOverrideConfig? = null,
    val session: SessionConfig? = null,
    val security: SecurityConfig? = null,
    val freeMarker: FreeMarkerConfig? = null,
    val swagger: SwaggerConfig? = null,
    val applicationMonitoring: ApplicationMonitoringConfig? = null,

    )
