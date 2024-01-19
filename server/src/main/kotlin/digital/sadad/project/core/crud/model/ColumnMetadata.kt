package digital.sadad.project.core.crud.model

import org.ufoss.kotysa.Column
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate

data class ColumnMetadata<T : Any>(
    val column: Column<T, *>,
    val isIdentity: Boolean = false,
    val valueGetter: (T) -> Any?,
    val valueSetter: (CoroutinesSqlClientDeleteOrUpdate.Update<T>, value: Any?) -> CoroutinesSqlClientDeleteOrUpdate.Update<T>,
)