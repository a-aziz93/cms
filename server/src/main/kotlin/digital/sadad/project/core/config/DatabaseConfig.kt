package digital.sadad.project.core.config

import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.auth.model.User
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
import org.ufoss.kotysa.h2.H2Table
import org.ufoss.kotysa.mariadb.MariadbTable
import org.ufoss.kotysa.mssql.MssqlTable
import org.ufoss.kotysa.mysql.MysqlTable
import org.ufoss.kotysa.oracle.OracleTable
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.r2dbc.coSqlClient
import kotlin.reflect.KClass

@Single
class DatabaseConfig(
    appConfig: AppConfig,
    private val config: Map<String, DatabaseConfig>? = appConfig.config.databases
) {

    val clients: Map<String, R2dbcSqlClient> =
        if (appConfig.config.databases == null) emptyMap()
        else appConfig.config.databases.map { it.key to
            it.value.getConnectionFactory().coSqlClient(
                when (it.value.driver) {
                    "h2" -> getH2TablesInPackage(it.value.tablesPackage)
                    "mysql" -> getMysqlTablesInPackage(it.value.tablesPackage)
                    "postgres" -> getPostgresqlTablesInPackage(it.value.tablesPackage)
                    "mssql" -> getMssqlTablesInPackage(it.value.tablesPackage)
                    "mariadb" -> getMariadbTablesInPackage(it.value.tablesPackage)
                    "oracle" -> getOracleTablesInPackage(it.value.tablesPackage)
                }
            )
        }.toMap()


    private val initDatabaseData by lazy {
        appConfig.applicationConfiguration.propertyOrNull("database.initDatabaseData")?.getString()?.toBoolean()
            ?: false
    }

    private val tablesPackages by lazy {
        appConfig.applicationConfiguration.propertyOrNull("database.tablesPackages")?.getList()
            ?: emptyList()
    }

    // Our client
    val client = connectionFactory.coSqlClient(getTables())

    init {
        logger.debug { "Init DataBaseService" }
        initDatabase()
    }

    // Our tables
    private fun getTables(): OracleTables {
        // Return tables
        return tables().oracle(tablesPackages.getTablesInPackage(, OracleTables::class))
    }

    private fun initDatabase() = runBlocking {
        logger.debug { "Init DatabaseService" }
        // Init data
        if (initDatabaseData) {
            clearDatabaseData()
            initDatabaseData()
        }
    }

    // Init data
    private suspend fun initDatabaseData() = withContext(Dispatchers.IO) {
        launch {
            logger.debug { "Creating the tables..." }
            client createTableIfNotExists UserTable
            logger.debug { "Saving initial users..." }
            userData().forEach {
                client insert it.value.copy(id = User.NEW_USER).toEntity()
            }

        }
    }

    // Clear all data
    private suspend fun clearDatabaseData() = withContext(Dispatchers.IO) {
        logger.debug { "Deleting data..." }
        launch {
            for (packageName in tablesPackages)
                for (table in getTablesInPackage(packageName, OracleTables::class)!!)
                    client deleteAllFrom table as Table<*>
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

        private fun <T : Table<*>> getTablesInPackage(packageName: String, type: KClass<T>): MutableSet<Class<KClass<T>>>? {
            val reflections = Reflections(packageName)
            return reflections.getSubTypesOf(type::class.java)
        }

        private fun getH2TablesInPackage(packageName: String): MutableSet<Class<out H2Table>>? =
            getTablesInPackage(packageName, H2Table::class)

        private fun getMysqlTablesInPackage(packageName: String): MutableSet<Class<out MysqlTable>>? =
            getTablesInPackage(packageName, MysqlTable::class)

        private fun getPostgresqlTablesInPackage(packageName: String): MutableSet<Class<out PostgresqlTable>>? =
            getTablesInPackage(packageName, PostgresqlTable::class)

        private fun getMssqlTablesInPackage(packageName: String): MutableSet<Class<out MssqlTable>>? =
            getTablesInPackage(packageName, MssqlTable::class)

        private fun getMariadbTablesInPackage(packageName: String): MutableSet<Class<out MariadbTable>>? =
            getTablesInPackage(packageName, MariadbTable::class)

        private fun getOracleTablesInPackage(packageName: String):OracleTables =
            tables().oracle(getTablesInPackage(packageName, OracleTable::class).toList())


    }
}