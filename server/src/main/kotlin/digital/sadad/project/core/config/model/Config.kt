package digital.sadad.project.core.config.model

import digital.sadad.project.core.config.model.template.Template
import digital.sadad.project.core.config.model.security.AuthConfig
import digital.sadad.project.core.config.model.cache.CacheConfig
import digital.sadad.project.core.config.model.compression.CompressionConfig
import digital.sadad.project.core.config.model.cors.CORSConfig
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.graphql.GraphQLConfig
import digital.sadad.project.core.config.model.ratelimit.RateLimitsConfig
import digital.sadad.project.core.config.model.serialization.SerializationConfig
import digital.sadad.project.core.config.model.session.SessionConfig
import digital.sadad.project.core.config.model.statuspages.StatusPagesConfig
import digital.sadad.project.core.config.model.storage.StorageConfig
import digital.sadad.project.core.config.model.swagger.SwaggerConfig
import digital.sadad.project.core.config.model.websocket.WebSocketsConfig
import digital.sadad.project.core.config.model.xhttpmethodoverride.XHttpMethodOverrideConfig

data class Config(
    val auth: AuthConfig? = null,
    val cache: Map<String, CacheConfig>? = null,
    val storage: StorageConfig? = null,
    val databases: Map<String, DatabaseConfig>? = null,
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
