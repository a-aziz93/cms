package digital.sadad.project.core.plugins.koin

import digital.sadad.project.auth.service.security.basic.BasicAuthService
import digital.sadad.project.auth.service.security.digest.DigestAuthService
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.config.model.Config
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.security.AuthConfig
import digital.sadad.project.core.database.service.DatabaseService
import digital.sadad.project.core.database.service.Companion.getConnectionFactory
import io.ktor.server.application.*
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ksp.generated.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
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

fun Application.configureKoin() {
    val appConfig: AppConfig by inject()

    install(Koin) {
        slf4jLogger() // Logger
        defaultModule() // Default module with Annotations
        appConfig.config.databases?.let { databaseClients(it) }
        appConfig.config.auth?.let { authServices(it) } // Auth services
    }
}

fun databaseClients(config: Map<String, DatabaseConfig>) = module {
    config.forEach {
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

            DbType.SQLITE -> throw UnsupportedOperationException("SQLite is not supported")
        }

        if (it.value.init != null) {
            runBlocking {
                initDatabase(client, createTables, it.value.init!!)
            }
        }

        it.key to client
    }
}

fun authServices(config: AuthConfig) = module {
    config.basic?.keys?.forEach { name ->
        single<BasicAuthService>(named(name)) { BasicAuthService(get(named(name))) }
    }
    config.digest?.keys?.forEach { name ->
        single<DigestAuthService>(named(name)) { DigestAuthService(get(named(name))) }
    }
}

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
): List<T> = packages.flatMap { it ->
    val reflections = Reflections(it)
    reflections.getSubTypesOf(type::class.java).map {
        it.genericInterfaces[0] as T
    }
}

private fun getH2TablesInPackage(packages: List<String>): H2Tables =
    tables().h2(
        *(getTablesInPackage(packages, IH2Table::class) + getTablesInPackage(
            packages,
            GenericTable::class
        )).toTypedArray()
    )

private fun getMariadbTablesInPackage(packages: List<String>): MariadbTables =
    tables().mariadb(*getTablesInPackage(packages, MariadbTable::class).toTypedArray())

private fun getMysqlTablesInPackage(packages: List<String>): MysqlTables =
    tables().mysql(*getTablesInPackage(packages, MysqlTable::class).toTypedArray())

private fun getMssqlTablesInPackage(packages: List<String>): MssqlTables =
    tables().mssql(
        *(getTablesInPackage(packages, MssqlTable::class) + getTablesInPackage(
            packages,
            GenericTable::class
        )).toTypedArray()
    )

private fun getPostgresqlTablesInPackage(packages: List<String>): PostgresqlTables =
    tables().postgresql(
        *(getTablesInPackage(packages, PostgresqlTable::class) + getTablesInPackage(
            packages,
            GenericTable::class
        )).toTypedArray()
    )

private fun getOracleTablesInPackage(packages: List<String>): OracleTables =
    tables().oracle(*getTablesInPackage(packages, OracleTable::class).toTypedArray())