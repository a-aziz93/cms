package digital.sadad.project.core.config.model

import org.ufoss.kotysa.DbType

data class DatabaseConfig(
    val type: DbType,
    val driver: String,
    val protocol: String,
    val user: String,
    val password: String,
    val database: String,
    val tablesPackage: String,
    val init: DatabaseInitConfig? = null,
)
