package digital.sadad.project.core.config.model

data class Config(
    val auth: AuthConfig? = null,
    val cache: Map<String, CacheConfig>? = null,
    val storage: StorageConfig? = null,
    val databases: Map<String, DatabaseConfig>? = null,
    val websockets: WebSocketsConfig? = null,
    val graphql: GraphQLConfig? = null,
)
