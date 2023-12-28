package core.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual class DatabaseDriverFactory {

    actual suspend fun Scope.createDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
        databaseName: String
    ): SqlDriver {
        return AndroidSqliteDriver(schema.synchronous(), androidContext(), "$databaseName.db")
    }
}