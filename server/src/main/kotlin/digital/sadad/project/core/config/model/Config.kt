package digital.sadad.project.core.config.model

import digital.sadad.project.core.config.model.plugin.template.Template
import digital.sadad.project.core.config.model.plugin.security.SecurityConfig
import digital.sadad.project.core.config.model.cache.CacheConfig
import digital.sadad.project.core.config.model.plugin.compression.CompressionConfig
import digital.sadad.project.core.config.model.plugin.cors.CORSConfig
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.plugin.ratelimit.RateLimitsConfig
import digital.sadad.project.core.config.model.plugin.serialization.SerializationConfig
import digital.sadad.project.core.config.model.plugin.session.SessionConfig
import digital.sadad.project.core.config.model.plugin.statuspages.StatusPagesConfig
import digital.sadad.project.core.config.model.storage.StorageConfig
import digital.sadad.project.core.config.model.plugin.swagger.SwaggerConfig
import digital.sadad.project.core.config.model.plugin.graphql.GraphQLConfig
import digital.sadad.project.core.config.model.plugin.websocket.WebSocketsConfig
import digital.sadad.project.core.config.model.plugin.xhttpmethodoverride.XHttpMethodOverrideConfig

data class Config(
    val cache: Map<String, CacheConfig>? = null,
    val storage: StorageConfig? = null,
    val database: Map<String, DatabaseConfig>? = null,
    val security: SecurityConfig? = null,
    val websocket: WebSocketsConfig? = null,
    val graphql: GraphQLConfig? = null,
    val template: Template? = null,
    val rateLimits: RateLimitsConfig? = null,
    val compression: CompressionConfig? = null,
    val cors: CORSConfig? = null,
    val serialization: SerializationConfig? = null,
    val swagger: SwaggerConfig? = null,
    val statusPages: StatusPagesConfig? = null,
    val xHttpMethodOverride: XHttpMethodOverrideConfig? = null,
    val session: SessionConfig? = null,
)
