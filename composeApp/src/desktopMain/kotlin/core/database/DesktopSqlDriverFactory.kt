package core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.scope.Scope
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import java.io.File


actual suspend fun Scope.sqlDriverFactory(schema:  SqlSchema<QueryResult.AsyncValue<Unit>>,databaseName:String): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), "${databaseName}.db")
    return JdbcSqliteDriver(url = "jdbc:sqlite:${databasePath.path}")
        .also { schema.create(it).await() }
}