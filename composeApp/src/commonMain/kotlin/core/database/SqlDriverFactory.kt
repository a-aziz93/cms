package core.database

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import org.koin.core.scope.Scope

expect suspend fun Scope.sqlDriverFactory(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, databaseName: String): SqlDriver
//fun createDatabase(driver: SqlDriver): SomeDatabase {
//    val database = SomeDatabase(
//        driver = driver,
//        )
//
//    return database
//}