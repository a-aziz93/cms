package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.Order
import core.crud.model.Page
import core.crud.model.PageResult
import core.crud.model.Update
import core.crud.model.predicate.Predicate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import java.time.LocalDateTime
import java.util.UUID
import kotlin.reflect.full.declaredMemberProperties

abstract class CRUDRepository<T : Any, ID : Any>(
    val client: R2dbcSqlClient,
    val table: Table<T>,
) : CRUD<T, ID> {


    protected abstract fun id(entity: T): ID?

    fun create(entity: T, username: String?, dateTime: LocalDateTime): T

    fun update(entity: T, username: String?, dateTime: LocalDateTime): T

    override suspend fun save(
        entities: List<T>,
        update: Update?,
        byUser: String?
    ): List<T> = client.transactional {
        withContext(Dispatchers.IO) {

            val result = arrayOfNulls<Any?>(entities.size) as Array<T>

            val createEntitiesWithIndexes: List<IndexedValue<T>> = emptyList()
            val updateEntitiesWithIndexes: List<IndexedValue<T>> = emptyList()

            for (indexedEntity in entities.withIndex()) {
                val id = id(indexedEntity.value)
                if (id == null || find(id) == null) {
                    createEntitiesWithIndexes.plus(indexedEntity)
                } else {
                    updateEntitiesWithIndexes.plus(indexedEntity)
                }
            }

            if (update != null) {
                for (indexedEntity in createEntitiesWithIndexes) {
                    client update table
                }
            }

            client.insertAndReturn(*arrayOf(createEntitiesWithIndexes.map {
                create(it.value, byUser, LocalDateTime.now())
            })).collect { it.forEachIndexed { index, value -> result[createEntitiesWithIndexes[index].index] = value } }

            return@withContext result.toList()
        }
    }!!

    override suspend fun find(id: ID): T? =
        id.check(client.selectFrom(table)).fetchFirstOrNull()

    suspend fun find(
        properties: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?,
        offset: Long? = null,
        limit: Long? = null
    ) {
        val selects = client.selects()

        properties?.forEach { projection ->
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

        var limitOffset: CoroutinesSqlClientSelect.LimitOffset<List<Any?>>? = null
        if (offset != null) {
            limitOffset = ordersBy.offset(offset)
        }

        if (limit != null) {
            if (limitOffset == null) {
                limitOffset = ordersBy.limit(limit)
            } else {
                limitOffset = limitOffset.limit(limit)
            }
        }

        return (limitOffset.fe ?: ordersBy).fetchAll()
    }

    override suspend fun find(properties: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> {

    }

    override suspend fun find(
        page: Page,
        projections: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?
    ): PageResult<T> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: ID): Boolean = id.check(client deleteFrom table).execute() > 0L

    suspend fun delete(entity: T): Boolean = delete(id(entity)!!)

    override suspend fun delete(predicate: Predicate?): Long {
        TODO("Not yet implemented")
    }

    override suspend fun count(predicate: Predicate?): Long {
        TODO("Not yet implemented")
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun ID.check(fromTable: CoroutinesSqlClientSelect.FromTable<T, T>): CoroutinesSqlClientSelect.Where<T> {
        for (prop in table::class.declaredMemberProperties) {
            when (prop.returnType.classifier) {
                LongDbIdentityColumnNotNull::class -> return fromTable.where(prop.call(table) as LongDbIdentityColumnNotNull<*>)
                    .eq(this as Long)

                UuidDbUuidColumnNullable::class -> return fromTable.where(prop.call(table) as UuidDbUuidColumnNullable<*>)
                    .eq(this as UUID)
            }
        }
        throw Exception("Table doesn't have an id column")
    }

    private fun ID.check(modify: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>): CoroutinesSqlClientDeleteOrUpdate.Where<T> {
        for (prop in table::class.declaredMemberProperties) {
            when (prop.returnType.classifier) {
                LongDbIdentityColumnNotNull::class -> return modify.where(prop.call(table) as LongDbIdentityColumnNotNull<*>)
                    .eq(this as Long)

                UuidDbUuidColumnNullable::class -> return modify.where(prop.call(table) as UuidDbUuidColumnNullable<*>)
                    .eq(this as UUID)
            }
        }
        throw Exception("Table doesn't have an id column")
    }

    private fun String.column(): Column<*, *> =
        table::class.declaredMemberProperties.find { it.name == this }?.let { it.call(table) as Column<*, *> }!!

}