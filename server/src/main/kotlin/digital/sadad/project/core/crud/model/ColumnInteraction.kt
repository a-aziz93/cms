package digital.sadad.project.core.crud.model

import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.SqlClientSelect

data class ColumnInteraction<T : Any>(
    val updateSetter: (CoroutinesSqlClientDeleteOrUpdate.Update<T>, value: Any?) -> CoroutinesSqlClientDeleteOrUpdate.Update<T>,
)
