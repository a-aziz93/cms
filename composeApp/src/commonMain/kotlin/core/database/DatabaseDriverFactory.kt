package core.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import org.koin.core.scope.Scope

expect class DatabaseDriverFactory() {
    suspend fun Scope.createDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
        databaseName: String
    ): SqlDriver
}

fun createDatabase(driverFactory:DatabaseDriverFactory): Database = Database(driverFactory.createDriver())

