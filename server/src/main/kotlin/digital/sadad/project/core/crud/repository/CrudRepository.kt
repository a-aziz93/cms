package digital.sadad.project.core.crud.repository

import digital.sadad.project.core.database.service.DatabaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.CoroutinesSqlClientSelect
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table
import java.time.LocalDateTime

/**
 * Define the CRUD operations of our application
 * based on a generic type T and ID
 * @param T Type of our entity
 * @param ID Type of our ID
 */
interface CrudRepository<T : Any, ID : Any> {
    val client: R2dbcSqlClient
    val table: Table<T>

    fun getId(entity: T): ID?

    fun create(entity: T, username: String?, dateTime: LocalDateTime): T

    fun update(entity: T, username: String?, dateTime: LocalDateTime): T

    fun checkSelectIdEquality(
        select: CoroutinesSqlClientSelect.FromTable<T, T>,
        id: ID
    ): CoroutinesSqlClientSelect.Return<T>

    fun checkModifyIdEquality(
        select: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>,
        id: ID
    ): CoroutinesSqlClientDeleteOrUpdate.Return

    suspend fun save(
        entities: List<T>,
        update: (T, CoroutinesSqlClientDeleteOrUpdate.Update<T>) -> CoroutinesSqlClientDeleteOrUpdate.Return,
        username: String?
    ): List<T> = withContext(Dispatchers.IO) {
        val result = arrayOfNulls<Any?>(entities.size) as Array<T?>

        val (insertEntitiesWithIndexes, updateEntitiesWithIndexes) = entities.withIndex()
            .partition { getId(it.value) != null }

        for (it in updateEntitiesWithIndexes) {
            if (find(getId(it.value)!!) == null) {

                val entity = update(it.value, username, LocalDateTime.now())

                update(entity, client update table).execute()

                result[it.index] = entity
            } else {
                insertEntitiesWithIndexes.plus(it)
            }
        }

        client.insertAndReturn(*insertEntitiesWithIndexes.map {
            create(it.value, username, LocalDateTime.now())
        }.toTypedArray(T::class))
            .collectIndexed { index, value -> result[insertEntitiesWithIndexes[index].index] = value }

        return@withContext result.map { it!! }
    }


    suspend fun find(predicate: (CoroutinesSqlClientSelect.FromTable<T, T>) -> CoroutinesSqlClientSelect.Return<T>): CoroutinesSqlClientSelect.FromTable<T, T> =
        withContext(Dispatchers.IO) {
            return@withContext client selectFrom table
        }

    suspend fun find(id: ID): T? = find { checkSelectIdEquality(it, id) }.fetchFirstOrNull()

    suspend fun delete(predicate: (CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>) -> CoroutinesSqlClientDeleteOrUpdate.Return): Long =
        withContext(Dispatchers.IO) {
            return@withContext (client deleteFrom table)
                .execute()
        }

    suspend fun delete(id: ID): Long = delete { checkModifyIdEquality(it, id) }

    suspend fun count(predicate: (CoroutinesSqlClientSelect.FromTable<Long, T>) -> CoroutinesSqlClientSelect.Where<Long>): Long =
        withContext(Dispatchers.IO) {
            return@withContext (client selectCountFrom table).fetchOne()!!
        }
}