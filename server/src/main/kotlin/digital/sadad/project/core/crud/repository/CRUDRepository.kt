package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.Order
import core.crud.model.predicate.Predicate
import digital.sadad.project.auth.entity.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import java.time.LocalDateTime
import java.util.UUID
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers

interface CRUDRepository<T : Any, ID : Any> : CRUD<T, ID> {
    val client: R2dbcSqlClient
    val table: Table<T>

    fun id(entity: T): ID?

    fun create(entity: T, username: String?, dateTime: LocalDateTime): T

    fun update(entity: T, username: String?, dateTime: LocalDateTime): T

    suspend fun save(
        entities: List<T>,
        update: (T, CoroutinesSqlClientDeleteOrUpdate.Update<T>) -> CoroutinesSqlClientDeleteOrUpdate.Return,
        username: String?
    ): List<T> = withContext(Dispatchers.IO) {
        val result = arrayOfNulls<Any?>(entities.size) as Array<T>

        var createEntitiesWithIndexes: List<IndexedValue<T>> = emptyList()

        for (it in entities.withIndex()) {
            val id = id(it.value)
            if (id == null || find(id) != null) {
                createEntitiesWithIndexes.plus(it)
            } else {

                val entity = update(it.value, username, LocalDateTime.now())

                (client.update(table)
                    .set(UserTable.active as BooleanColumnNotNull<T>).eq(true))

                update(entity, client update table).execute()

                result[it.index] = entity
            }
        }

        client.insertAndReturn(*arrayOf(createEntitiesWithIndexes.map {
            create(it.value, username, LocalDateTime.now())
        })).collect { it.forEachIndexed { index, value -> result[createEntitiesWithIndexes[index].index] = value } }

        return@withContext result.toList()
    }

    override suspend fun find(id: ID): T? =
        id.checkEquality(client.selectFrom(table)).fetchFirstOrNull()

    override suspend fun find(projections: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> {
        val selects = client.selects()

        projections?.forEach { projection ->
            selects.select(projection.column())
        }

        val froms = selects.froms()
        froms.from(table)

        val wheres = froms.wheres()
        if (predicate != null) {

        }

        val ordersBy = wheres.ordersBy()

        sort?.forEach {
            if (it.ascending) {
                ordersBy.orderByAsc(it.name.column())
            } else {
                ordersBy.orderByDesc(it.name.column())
            }
        }

        return ordersBy.fetchAll()
    }

    override suspend fun delete(id: ID): Boolean = id.checkEquality(client deleteFrom table).execute() > 0L

    suspend fun delete(entity: T): Boolean = delete(id(entity)!!)

    private fun ID.checkEquality(fromTable: CoroutinesSqlClientSelect.FromTable<T, T>): CoroutinesSqlClientSelect.Where<T> {
        for (prop in table::class.declaredMemberProperties) {
            when (prop.returnType) {
                is LongDbIdentityColumnNotNull<*> -> return fromTable.where(prop as LongDbIdentityColumnNotNull<*>)
                    .eq(this as Long)

                is UuidDbUuidColumnNullable<*> -> return fromTable.where(prop as UuidDbUuidColumnNullable<*>)
                    .eq(this as UUID)
            }
        }
        throw Exception("Table doesn't have an id column")
    }

    private fun ID.checkEquality(modify: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>): CoroutinesSqlClientDeleteOrUpdate.Where<T> {
        for (prop in table::class.declaredMemberProperties) {
            when (prop.returnType) {
                is LongDbIdentityColumnNotNull<*> -> return modify.where(prop as LongDbIdentityColumnNotNull<*>)
                    .eq(this as Long)

                is UuidDbUuidColumnNullable<*> -> return modify.where(prop as UuidDbUuidColumnNullable<*>)
                    .eq(this as UUID)
            }
        }
        throw Exception("Table doesn't have an id column")
    }

    private fun String.column(): Column<*, T> =
        table::class.declaredMemberProperties.find { it.name == this } as Column<*, T>

}