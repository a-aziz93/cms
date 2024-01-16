package digital.sadad.project.core.crud.model

import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate

data class ColumnInteraction<T : Any>(
    val updateSetter: (CoroutinesSqlClientDeleteOrUpdate.Update<T>, value: Any?) -> CoroutinesSqlClientDeleteOrUpdate.Update<T>,
)
