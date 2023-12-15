package core.database

import org.koin.core.scope.Scope
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema


actual fun Scope.sqlDriverFactory(sqlSchema:  SqlSchema<QueryResult.Value<Unit>>,databaseName:String): SqlDriver {
    return WebWorkerDriver(
        Worker(
            js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
        )
    ).also { schema.create(it).await() }
}