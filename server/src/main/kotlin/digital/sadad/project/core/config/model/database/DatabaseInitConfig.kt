package digital.sadad.project.core.config.model.database

data class DatabaseInitConfig(
    val packages: Set<String>,
    val tables: Set<String>? = null,
    val tablesInclusive: Boolean? = null,
    val ifNotExists: Boolean = true,
)