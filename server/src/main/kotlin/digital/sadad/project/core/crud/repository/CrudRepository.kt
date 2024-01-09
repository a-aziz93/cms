package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.*
import java.time.LocalDateTime

interface CrudRepository<T : Any, ID : Any> : CRUD<T, ID> {
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
        val result = arrayOfNulls<Any?>(entities.size) as Array<T>

        var createEntitiesWithIndexes: List<IndexedValue<T>> = emptyList()

        for (it in entities.withIndex()) {
            val id = getId(it.value)

            if (id == null || find(getId(it.value)!!) != null) {
                createEntitiesWithIndexes.plus(it)
            } else {

                val entity = update(it.value, username, LocalDateTime.now())

                client update table

                update(entity, client update table).execute()

                result[it.index] = entity
            }
        }

        client.insertAndReturn(*arrayOf(createEntitiesWithIndexes.map {
            create(it.value, username, LocalDateTime.now())
        })).collect { it.forEachIndexed { index, value -> result[createEntitiesWithIndexes[index].index] = value } }

        return@withContext result.toList()
    }


    suspend fun find(
        predicate: (CoroutinesSqlClientSelect.FromTable<T, T>) -> CoroutinesSqlClientSelect.Return<T> = { it }
    ): CoroutinesSqlClientSelect.Return<T> =
        withContext(Dispatchers.IO) {
            return@withContext predicate(client selectFrom table)
        }

    suspend fun <V : Any> find(
        builder: (ValueProvider) -> V,
        predicate: (CoroutinesSqlClientSelect.FromTable<V, T>) -> CoroutinesSqlClientSelect.Return<V> = { it },
    ): CoroutinesSqlClientSelect.Return<V> =
        withContext(Dispatchers.IO) {
            return@withContext predicate(client.selectAndBuild { builder(it) } from table)
        }

    suspend fun find(id: ID): T? = find { checkSelectIdEquality(it, id) }.fetchFirstOrNull()

    suspend fun delete(predicate: (CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>) -> CoroutinesSqlClientDeleteOrUpdate.Return = { it }): Long =
        withContext(Dispatchers.IO) {
            return@withContext (client deleteFrom table)
                .execute()
        }

    suspend fun delete(id: ID): Boolean = delete { checkModifyIdEquality(it, id) } == 0L

    suspend fun delete(entity: T): Boolean = delete(getId(entity)!!)

    suspend fun count(predicate: (CoroutinesSqlClientSelect.FromTable<Long, T>) -> CoroutinesSqlClientSelect.Return<Long> = { it }): Long =
        withContext(Dispatchers.IO) {
            return@withContext predicate(client selectCountFrom table).fetchOne()!!
        }
}