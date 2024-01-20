package digital.sadad.project.core.config.model.database

import org.ufoss.kotysa.DbType
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class DatabaseConfig(
    val type: DbType,
    val driver: String,
    val protocol: String? = null,
    val host: String? = null,
    val port: Int? = null,
    val database: String,
    val ssl: Boolean = false,
    val user: String,
    val password: String,
    val connectTimeout: Duration = 15.toDuration(DurationUnit.SECONDS),
    val lockWaitTimeout: Duration = 15.toDuration(DurationUnit.SECONDS),
    val statementTimeout: Duration = 15.toDuration(DurationUnit.SECONDS),
    val packages: List<String>,
    val init: DatabaseInitConfig? = null,
)
