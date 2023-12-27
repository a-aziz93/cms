package digital.sadad.project.core.config.model

data class Config(
    val auth: AuthConfig? = null,
    val databases: Map<String, DatabaseConfig>? = null,
)
