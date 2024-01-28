package digital.sadad.project.core.plugin.di.module.database

import core.user.model.entity.UserEntity
import digital.sadad.project.cms.di.cmsModule
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.database.DatabaseInitConfig
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import digital.sadad.project.core.role.model.entity.RoleTable
import digital.sadad.project.core.role.repository.RoleRepository
import digital.sadad.project.core.role.service.RoleService
import digital.sadad.project.core.role.service.RoleServiceImpl
import digital.sadad.project.core.route.model.entity.RouteTable
import digital.sadad.project.core.route.repository.RouteRepository
import digital.sadad.project.core.route.service.RouteService
import digital.sadad.project.core.route.service.RouteServiceImpl
import digital.sadad.project.core.routerole.model.entity.RouteRoleTable
import digital.sadad.project.core.routerole.repository.RouteRoleRepository
import digital.sadad.project.core.routerole.service.RouteRoleService
import digital.sadad.project.core.routerole.service.RouteRoleServiceImpl
import digital.sadad.project.core.user.model.entity.UserTable
import digital.sadad.project.core.user.repository.UserRepository
import digital.sadad.project.core.user.service.UserService
import digital.sadad.project.core.user.service.UserServiceImpl
import digital.sadad.project.core.userrole.model.entity.UserRoleTable
import digital.sadad.project.core.userrole.repository.UserRoleRepository
import digital.sadad.project.core.userrole.service.UserRoleService
import digital.sadad.project.core.userrole.service.UserRoleServiceImpl
import digital.sadad.project.map.di.mapModule
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
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

fun databaseModule(config: Map<String?, DatabaseConfig>) = module {
    config.forEach { (name, config) ->
        var createTables: Set<Table<*>>? = null
        val client = when (config.type) {
            DbType.H2 -> {
                val tables = getH2TablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.MARIADB -> {
                val tables = getMariadbTablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.MYSQL -> {
                val tables = getMysqlTablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.MSSQL -> {
                val tables = getMssqlTablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.POSTGRESQL -> {
                val tables = getPostgresqlTablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.ORACLE -> {
                val tables = getOracleTablesInPackage(config.packages)
                createTables = tables.allTables.keys
                config.getConnectionFactory()
                    .coSqlClient(tables)
            }

            DbType.SQLITE -> throw UnsupportedOperationException("SQLite is not supported")
        }

        if (config.init != null) {
            runBlocking {
                initDatabase(client, createTables, config.init)
            }
        }

        single<R2dbcSqlClient>(name?.let { named(it) }) { client }

        // USER
        single<UserRepository>(name?.let { named(it) }) { UserRepository(client, UserTable) }
        single<UserService>(name?.let { named(it) }) { UserServiceImpl(get(name?.let { named(it) })) }

        // ROLE
        single<RoleRepository>(name?.let { named(it) }) { RoleRepository(client, RoleTable) }
        single<RoleService>(name?.let { named(it) }) { RoleServiceImpl(get(name?.let { named(it) })) }

        // USER<->ROLE
        single<UserRoleRepository>(name?.let { named(it) }) { UserRoleRepository(client, UserRoleTable) }
        single<UserRoleService>(name?.let { named(it) }) { UserRoleServiceImpl(get(name?.let { named(it) })) }

        // ROUTE
        single<RouteRepository>(name?.let { named(it) }) { RouteRepository(client, RouteTable) }
        single<RouteService>(name?.let { named(it) }) { RouteServiceImpl(get(name?.let { named(it) })) }

        // ROUTE<->ROLE
        single<RouteRoleRepository>(name?.let { named(it) }) { RouteRoleRepository(client, RouteRoleTable) }
        single<RouteRoleService>(name?.let { named(it) }) { RouteRoleServiceImpl(get(name?.let { named(it) })) }

        // FEATURE MODULES

        // map
        mapModule(client, name)

        // cms
        cmsModule(client, name)
    }
}

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