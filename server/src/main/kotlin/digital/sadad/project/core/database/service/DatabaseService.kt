package digital.sadad.project.core.database.service

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.database.DatabaseInitConfig
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.*
import org.koin.core.annotation.Single
import org.reflections.Reflections
import org.ufoss.kotysa.*
import org.ufoss.kotysa.h2.IH2Table
import org.ufoss.kotysa.mariadb.MariadbTable
import org.ufoss.kotysa.mssql.MssqlTable
import org.ufoss.kotysa.mysql.MysqlTable
import org.ufoss.kotysa.oracle.OracleTable
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.r2dbc.coSqlClient
import kotlin.reflect.KClass

@Single
class DatabaseService(
    appConfig: AppConfig,
    private val config: Map<String, DatabaseConfig>? = appConfig.config.databases
) {


    // Init data
    private suspend fun initDatabase(client: R2dbcSqlClient, tables: Set<Table<*>>, config: DatabaseInitConfig) =
        withContext(Dispatchers.IO) {
            launch {
                if (config.ifNotExists) {
                    for (table in tables) {
                        client createTableIfNotExists table
                    }
                } else {
                    if (config.clearBefore) {
                        for (table in tables) {
                            client deleteAllFrom table
                        }
                    }
                    for (table in tables) {
                        client createTable table
                    }
                }
            }
        }

    companion object {



    }
}