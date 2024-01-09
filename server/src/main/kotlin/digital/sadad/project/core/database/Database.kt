package digital.sadad.project.core.database

import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.DatabaseConfig
import digital.sadad.project.core.config.model.DatabaseInitConfig
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.*
import org.koin.core.annotation.Single
import org.reflections.Reflections
import org.ufoss.kotysa.*
import org.ufoss.kotysa.h2.H2Table
import org.ufoss.kotysa.h2.IH2Table
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
            var createTables: Set<Table<*>>? = null
            val client = when (it.value.type) {
                DbType.H2 -> {
                    val tables = getH2TablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.MARIADB -> {
                    val tables = getMariadbTablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.MYSQL -> {
                    val tables = getMysqlTablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.MSSQL -> {
                    val tables = getMssqlTablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.POSTGRESQL -> {
                    val tables = getPostgresqlTablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.ORACLE -> {
                    val tables = getOracleTablesInPackage(it.value.packages)
                    createTables = tables.allTables.keys
                    it.value.getConnectionFactory()
                        .coSqlClient(tables)
                }

                DbType.SQLITE -> null
            }
            if (client == null) {
                null
            } else {
                if (it.value.init != null && createTables != null) {
                    runBlocking {
                        initDatabase(client, createTables, it.value.init!!)
                    }
                }
                it.key to client
            }
        }.toMap()

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
        private fun DatabaseConfig.getConnectionFactory(): ConnectionFactory {
            val connectionBuilder = ConnectionFactoryOptions.builder()
                .option(
                    ConnectionFactoryOptions.DRIVER,
                    driver
                )
                .option(
                    ConnectionFactoryOptions.USER,
                    user
                )
                .option(
                    ConnectionFactoryOptions.PASSWORD,
                    password
                )
                .option(
                    ConnectionFactoryOptions.DATABASE,
                    this.database
                )
            if (protocol != null) {
                connectionBuilder
                    .option(
                        ConnectionFactoryOptions.PROTOCOL,
                        protocol
                    )
            }
            if (host != null) {
                connectionBuilder
                    .option(
                        ConnectionFactoryOptions.HOST,
                        host
                    )
            }
            if (port != null) {
                connectionBuilder
                    .option(
                        ConnectionFactoryOptions.PORT,
                        port
                    )
            }
            return ConnectionFactories.get(
                connectionBuilder.build()
            )
        }

        private fun <T : Table<*>> getTablesInPackage(
            packages: List<String>,
            type: KClass<T>
        ): List<T> {
            val reflections = Reflections(packages[0])
            return reflections.getSubTypesOf(type::class.java)
        }

        private fun getH2TablesInPackage(packages: List<String>): H2Tables =
            tables().h2(*getTablesInPackage(packages, IH2Table::class).toTypedArray())

        private fun getMariadbTablesInPackage(packages: List<String>): MariadbTables =
            tables().mariadb(*getTablesInPackage(packages, MariadbTable::class).toTypedArray())

        private fun getMysqlTablesInPackage(packages: List<String>): MysqlTables =
            tables().mysql(*getTablesInPackage(packages, MysqlTable::class).toTypedArray())

        private fun getMssqlTablesInPackage(packages: List<String>): MssqlTables =
            tables().mssql(*getTablesInPackage(packages, MssqlTable::class).toTypedArray())


        private fun getPostgresqlTablesInPackage(packages: List<String>): PostgresqlTables =
            tables().postgresql(*getTablesInPackage(packages, PostgresqlTable::class).toTypedArray())

        private fun getOracleTablesInPackage(packages: List<String>): OracleTables =
            tables().oracle(*getTablesInPackage(packages, OracleTable::class).toTypedArray())


    }
}