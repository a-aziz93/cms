package digital.sadad.project.core.plugin.di.module.database

import digital.sadad.project.cms.di.cmsModule
import digital.sadad.project.core.config.model.database.DatabaseConfig
import digital.sadad.project.core.config.model.database.DatabaseInitConfig
import digital.sadad.project.core.security.role.model.entity.RoleTable
import digital.sadad.project.core.security.role.repository.RoleCRUDRepository
import digital.sadad.project.core.security.role.service.RoleService
import digital.sadad.project.core.security.role.service.RoleServiceImpl
import digital.sadad.project.core.security.route.model.entity.RouteTable
import digital.sadad.project.core.security.route.repository.RouteCRUDRepository
import digital.sadad.project.core.security.route.service.RouteService
import digital.sadad.project.core.security.route.service.RouteServiceImpl
import digital.sadad.project.core.security.routerole.model.entity.RouteRoleTable
import digital.sadad.project.core.security.routerole.repository.RouteRoleCRUDRepository
import digital.sadad.project.core.security.routerole.service.RouteRoleService
import digital.sadad.project.core.security.routerole.service.RouteRoleServiceImpl
import digital.sadad.project.core.security.user.model.entity.UserTable
import digital.sadad.project.core.security.user.repository.UserCRUDRepository
import digital.sadad.project.core.security.user.service.UserService
import digital.sadad.project.core.security.user.service.UserServiceImpl
import digital.sadad.project.core.security.userrole.model.entity.UserRoleTable
import digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository
import digital.sadad.project.core.security.userrole.service.UserRoleService
import digital.sadad.project.core.security.userrole.service.UserRoleServiceImpl
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
import org.ufoss.kotysa.mssql.IMssqlTable
import org.ufoss.kotysa.mssql.MssqlTable
import org.ufoss.kotysa.mysql.MysqlTable
import org.ufoss.kotysa.oracle.OracleTable
import org.ufoss.kotysa.postgresql.IPostgresqlTable
import org.ufoss.kotysa.postgresql.PostgresqlTable
import org.ufoss.kotysa.r2dbc.coSqlClient
import kotlin.reflect.KClass

fun databaseModule(config: Set<DatabaseConfig>) = module {
    config.forEach { cfg ->
        var initTables: List<Pair<List<Table<*>>, Boolean>>? = null
        val client = when (cfg.type) {
            DbType.H2 -> {
                initTables = getInitTables(cfg.init) { getH2TablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().h2(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.MARIADB -> {
                initTables = getInitTables(cfg.init) { getMariadbTablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().mariadb(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.MYSQL -> {
                initTables = getInitTables(cfg.init) { getMysqlTablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().mysql(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.MSSQL -> {
                initTables = getInitTables(cfg.init) { getMssqlTablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().mssql(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.POSTGRESQL -> {
                initTables = getInitTables(cfg.init) { getPostgresqlTablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().postgresql(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.ORACLE -> {
                initTables = getInitTables(cfg.init) { getOracleTablesInPackage(it) }
                cfg.getConnectionFactory()
                    .coSqlClient(tables().oracle(*initTables.flatMap { it.first }.toTypedArray()))
            }

            DbType.SQLITE -> throw UnsupportedOperationException("SQLite is not supported")
        }

        cfg.init?.let {
            runBlocking {
                initDatabase(client, initTables)
            }
        }

        single<R2dbcSqlClient>(cfg.name?.let { named(it) }) { client }

        // USER
        single<UserCRUDRepository>(cfg.name?.let { named(it) }) {
            UserCRUDRepository(
                client,
                UserTable
            )
        }
        single<UserService>(cfg.name?.let { named(it) }) {
            UserServiceImpl(
                get(cfg.name?.let { named(it) })
            )
        }

        // ROLE
        single<RoleCRUDRepository>(cfg.name?.let { named(it) }) {
            RoleCRUDRepository(
                client,
                RoleTable
            )
        }
        single<RoleService>(cfg.name?.let { named(it) }) {
            RoleServiceImpl(
                get(cfg.name?.let { named(it) })
            )
        }

        // USER<->ROLE
        single<UserRoleCRUDRepository>(cfg.name?.let { named(it) }) {
            UserRoleCRUDRepository(
                client,
                UserRoleTable
            )
        }
        single<UserRoleService>(cfg.name?.let { named(it) }) {
            UserRoleServiceImpl(
                get(cfg.name?.let { named(it) }),
                get(cfg.name?.let { named(it) }),
            )
        }

        // ROUTE
        single<RouteCRUDRepository>(cfg.name?.let { named(it) }) {
            RouteCRUDRepository(
                client,
                RouteTable
            )
        }
        single<RouteService>(cfg.name?.let { named(it) }) {
            RouteServiceImpl(
                get(cfg.name?.let { named(it) })
            )
        }

        // ROUTE<->ROLE
        single<RouteRoleCRUDRepository>(cfg.name?.let { named(it) }) {
            RouteRoleCRUDRepository(
                client,
                RouteRoleTable
            )
        }
        single<RouteRoleService>(cfg.name?.let { named(it) }) {
            RouteRoleServiceImpl(
                get(cfg.name?.let { named(it) })
            )
        }

        // FEATURE MODULES

        // map
        mapModule(client, cfg.name)

        // cms
        cmsModule(client, cfg.name)
    }
}

private suspend fun initDatabase(
    client: R2dbcSqlClient,
    initTables: List<Pair<List<Table<*>>, Boolean>>,
) =
    withContext(Dispatchers.IO) {
        launch {
            initTables.forEach {
                if (it.second) {
                    for (table in it.first) {
                        client createTableIfNotExists table
                    }
                } else {
                    for (table in it.first) {
                        client deleteAllFrom table
                        client createTable table
                    }
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
    packages: Set<String>,
    type: KClass<T>
): List<T> = packages.flatMap { it ->
    val reflections = Reflections(it)
    reflections.getSubTypesOf(type::class.java).map {
        it.genericInterfaces[0] as T
    }
}

private fun getH2TablesInPackage(packages: Set<String>): List<IH2Table<*>> =
    getTablesInPackage(packages, IH2Table::class) + getTablesInPackage(
        packages,
        GenericTable::class
    )


private fun getMariadbTablesInPackage(packages: Set<String>): List<MariadbTable<*>> =
    getTablesInPackage(packages, MariadbTable::class)

private fun getMysqlTablesInPackage(packages: Set<String>): List<MysqlTable<*>> =
    getTablesInPackage(packages, MysqlTable::class)

private fun getMssqlTablesInPackage(packages: Set<String>): List<IMssqlTable<*>> =
    getTablesInPackage(packages, MssqlTable::class) + getTablesInPackage(
        packages,
        GenericTable::class
    )

private fun getPostgresqlTablesInPackage(packages: Set<String>): List<IPostgresqlTable<*>> =
    getTablesInPackage(packages, PostgresqlTable::class) + getTablesInPackage(
        packages,
        GenericTable::class
    )

private fun getOracleTablesInPackage(packages: Set<String>): List<OracleTable<*>> =
    getTablesInPackage(packages, OracleTable::class)

private fun <T : Table<*>> getInitTables(
    init: List<DatabaseInitConfig>?,
    getTables: (Set<String>) -> List<T>,
): List<Pair<List<T>, Boolean>> =
    init?.map {
        val tables = getTables(it.packages)
        if (it.tablesInclusive == true) {
            it.tables?.let { ts -> tables.filter { ts.contains(it::class.qualifiedName) } } ?: tables
        } else {
            it.tables?.let { ts -> tables.filter { !ts.contains(it::class.qualifiedName) } } ?: tables
        }
        tables to it.ifNotExists
    } ?: emptyList()