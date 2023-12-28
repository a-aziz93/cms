package core.database

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.scope.Scope
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

actual class DatabaseDriverFactory {
    actual suspend fun Scope.createDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
        databaseName: String
    ): SqlDriver {
        return NativeSqliteDriver(schema.synchronous(), "$databaseName.db")
    }
}