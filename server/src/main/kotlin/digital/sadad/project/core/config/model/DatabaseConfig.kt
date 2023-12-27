package digital.sadad.project.core.config.model

data class DatabaseConfig(
    val driver: String,
    val protocol: String,
    val user: String,
    val password: String,
    val database: String,
    val init: Boolean,
)
