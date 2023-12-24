package digital.sadad.project.database.service

import digital.sadad.project.config.AppConfig
import digital.sadad.project.database.logger
import digital.sadad.project.user.data.userDemoData
import digital.sadad.project.user.entity.UserTable
import digital.sadad.project.user.mapper.toEntity
import digital.sadad.project.user.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * DataBase Service to connect to our database
 * @property myConfig AppConfig Configuration of our service
 */

@Singleton
class DataBaseService(
    private val myConfig: AppConfig,
) {

    private val connectionFactory by lazy {
        val options = ConnectionFactoryOptions.builder()
            .option(
                ConnectionFactoryOptions.DRIVER,
                myConfig.applicationConfiguration.propertyOrNull("database.driver")?.getString() ?: "h2"
            )
            .option(
                ConnectionFactoryOptions.PROTOCOL,
                myConfig.applicationConfiguration.propertyOrNull("database.protocol")?.getString() ?: "mem"
            )
            .option(
                ConnectionFactoryOptions.USER,
                myConfig.applicationConfiguration.propertyOrNull("database.user")?.getString() ?: "sa"
            )
            .option(
                ConnectionFactoryOptions.PASSWORD,
                myConfig.applicationConfiguration.propertyOrNull("database.password")?.getString() ?: ""
            )
            .option(
                ConnectionFactoryOptions.DATABASE,
                myConfig.applicationConfiguration.propertyOrNull("database.database")?.getString()
                    ?: "r2dbc:h2:mem:///test;DB_CLOSE_DELAY=-1"
            )
            .build()
        ConnectionFactories.get(options)
    }

    private val initDatabaseData by lazy {
        myConfig.applicationConfiguration.propertyOrNull("database.initDatabaseData")?.getString()?.toBoolean() ?: false
    }

    // Our client
    val client = connectionFactory.coSqlClient(getTables())

    init {
        logger.debug { "Init DataBaseService" }
        initDatabase()
    }

    // Our tables
    private fun getTables(): H2Tables {
        // Return tables
        return tables().h2(UserTable)
    }

    private fun initDatabase() = runBlocking {
        logger.debug { "Init DatabaseService" }
        createTables()
        // Init data
        if (initDatabaseData) {
            initDataBaseDataDemo()
        }
    }

    // demo data
    suspend fun initDataBaseDataDemo() {
        clearDataBaseData()
        initDataBaseData()
    }

    // Create tables if not exists
    private suspend fun createTables() = withContext(Dispatchers.IO) {
        logger.debug { "Creating the tables..." }
        launch {
            client createTableIfNotExists UserTable
        }
    }

    // Clear all data
    private suspend fun clearDataBaseData() = withContext(Dispatchers.IO) {
        logger.debug { "Deleting data..." }
        launch {
            client deleteAllFrom UserTable
        }
    }

    // Init data
    private suspend fun initDataBaseData() = withContext(Dispatchers.IO) {
        logger.debug { "Saving demo data..." }
        launch {
            logger.debug { "Saving demo users..." }
            userDemoData().forEach {
                client insert it.value.copy(id = User.NEW_USER).toEntity()
            }

        }
    }
}