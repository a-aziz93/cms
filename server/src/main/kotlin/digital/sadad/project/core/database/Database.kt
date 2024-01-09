package digital.sadad.project.core.database

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.DatabaseConfig
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import org.reflections.Reflections
import org.ufoss.kotysa.*
import org.ufoss.kotysa.mariadb.MariadbTable
import org.ufoss.kotysa.mssql.MssqlTable
import org.ufoss.kotysa.mysql.MysqlTable
import org.ufoss.kotysa.oracle.OracleTable
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.r2dbc.coSqlClient
import kotlin.reflect.KClass

@Single
class Database(
    appConfig: AppConfig,
    private val config: Map<String, DatabaseConfig>? = appConfig.config.databases
) {
    val clients: Map<String, R2dbcSqlClient> =
        if (appConfig.config.databases == null) emptyMap()
        else appConfig.config.databases.mapNotNull {
            val client = when (it.value.type) {
                DbType.H2 -> it.value.getConnectionFactory()
                    .coSqlClient(tables=getH2TablesInPackage(it.value.tablesPackage))

                DbType.MYSQL -> it.value.getConnectionFactory()
                    .coSqlClient(getMysqlTablesInPackage(it.value.tablesPackage))

                DbType.POSTGRESQL-> it.value.getConnectionFactory()
                    .coSqlClient(getPostgresqlTablesInPackage(it.value.tablesPackage))

                DbType.MSSQL-> it.value.getConnectionFactory()
                    .coSqlClient(getMssqlTablesInPackage(it.value.tablesPackage))

                DbType.MARIADB -> it.value.getConnectionFactory()
                    .coSqlClient(getMariadbTablesInPackage(it.value.tablesPackage))

                DbType.ORACLE -> it.value.getConnectionFactory()
                    .coSqlClient(getOracleTablesInPackage(it.value.tablesPackage))
            }
//
//                clearDatabaseData()
//                initDatabaseData()
                it.key to client
        }.toMap()

    // Init data
    private suspend fun initDatabaseData(client: R2dbcSqlClient, table: Tables) = withContext(Dispatchers.IO) {
        launch {
            client createTableIfNotExists tables
        }
    }

    // Clear all data
    private suspend fun clearDatabaseData(client: R2dbcSqlClient, table: Table<*>) = withContext(Dispatchers.IO) {
        launch {
            client deleteAllFrom table
        }
    }

    companion object {
        private fun DatabaseConfig.getConnectionFactory() = ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(
                    ConnectionFactoryOptions.DRIVER,
                    this.driver
                )
                .option(
                    ConnectionFactoryOptions.PROTOCOL,
                    this.protocol
                )
                .option(
                    ConnectionFactoryOptions.USER,
                    this.user
                )
                .option(
                    ConnectionFactoryOptions.PASSWORD,
                    this.password
                )
                .option(
                    ConnectionFactoryOptions.DATABASE,
                    this.database
                )
                .build()
        )

        private fun <T : Table<*>> getTablesInPackage(
            packageName: String,
            type: KClass<T>
        ): MutableSet<Class<KClass<T>>>? {
            val reflections = Reflections(packageName)
            return reflections.getSubTypesOf(type::class.java)
        }

        private fun getH2TablesInPackage(packageName: String): H2Tables {
            tables().h2()
//            getTablesInPackage(packageName, H2Table::class)
        }

        private fun getMysqlTablesInPackage(packageName: String): MysqlTables =
            getTablesInPackage(packageName, MysqlTable::class)

        private fun getPostgresqlTablesInPackage(packageName: String): PostgresqlTables =
            getTablesInPackage(packageName, PostgresqlTable::class)

        private fun getMssqlTablesInPackage(packageName: String): MssqlTables =
            getTablesInPackage(packageName, MssqlTable::class)

        private fun getMariadbTablesInPackage(packageName: String): MariadbTables =
            getTablesInPackage(packageName, MariadbTable::class)

        private fun getOracleTablesInPackage(packageName: String): OracleTables =
            tables().oracle(getTablesInPackage(packageName, OracleTable::class).toList())


    }
}