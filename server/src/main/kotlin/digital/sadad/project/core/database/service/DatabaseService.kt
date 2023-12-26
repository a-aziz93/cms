package digital.sadad.project.core.database.service

import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.auth.data.userData
import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.auth.mapper.toEntity
import digital.sadad.project.auth.model.User
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.two.KotlinLogging
import org.koin.core.annotation.Singleton
import org.reflections.Reflections
import org.ufoss.kotysa.OracleTables
import org.ufoss.kotysa.Table
import org.ufoss.kotysa.r2dbc.coSqlClient
import org.ufoss.kotysa.tables
import kotlin.reflect.KClass


private val logger = KotlinLogging.logger {}

/**
 * DataBase Service to connect to our database
 * @property appConfig AppConfig Configuration of our service
 */

@Singleton
class DatabaseService(
    private val appConfig: AppConfig,
) {

    private val connectionFactory by lazy {
        val options = ConnectionFactoryOptions.builder()
            .option(
                ConnectionFactoryOptions.DRIVER,
                appConfig.applicationConfiguration.propertyOrNull("database.driver")?.getString() ?: "h2"
            )
            .option(
                ConnectionFactoryOptions.PROTOCOL,
                appConfig.applicationConfiguration.propertyOrNull("database.protocol")?.getString() ?: "mem"
            )
            .option(
                ConnectionFactoryOptions.USER,
                appConfig.applicationConfiguration.propertyOrNull("database.user")?.getString() ?: "sa"
            )
            .option(
                ConnectionFactoryOptions.PASSWORD,
                appConfig.applicationConfiguration.propertyOrNull("database.password")?.getString() ?: ""
            )
            .option(
                ConnectionFactoryOptions.DATABASE,
                appConfig.applicationConfiguration.propertyOrNull("database.database")?.getString()
                    ?: "r2dbc:h2:mem:///test;DB_CLOSE_DELAY=-1"
            )
            .build()
        ConnectionFactories.get(options)
    }

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
        private fun <T : Any> getTablesInPackage(packageName: String, type: KClass<T>): MutableSet<Class<out T>>? {
            val reflections = Reflections(packageName)
            return reflections.getSubTypesOf(type.java)
        }
    }
}

