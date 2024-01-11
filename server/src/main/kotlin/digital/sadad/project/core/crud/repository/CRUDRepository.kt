package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.Order
import core.crud.model.Page
import core.crud.model.PageResult
import core.crud.model.Update
import core.crud.model.predicate.Predicate
import core.crud.model.predicate.PredicateField.Companion.field
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import java.time.LocalDateTime
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties

abstract class CRUDRepository<T : Any, ID : Any>(
    protected val client: R2dbcSqlClient,
    protected val table: Table<T>,
    val tableIdColumnName: String = table::class.declaredMemberProperties.find {
        it.returnType.classifier == LongDbIdentityColumnNotNull::class || it.returnType.classifier == UuidDbUuidColumnNullable::class
    }!!.name,
    val createByColumnName: String? = null,
    val createDateTimeColumnName: String? = null,
    val updateByColumnName: String? = null,
    val updateDateTimeColumnName: String? = null,
) : CRUD<T, ID> {

    protected abstract fun entityId(entity: T): ID?

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
                val id = entityId(indexedEntity.value)
                if (id == null || find(predicate = field(tableIdColumnName).eq(id)).firstOrNull() == null) {
                    createEntitiesWithIndexes + indexedEntity
                } else {
                    updateEntitiesWithIndexes + indexedEntity
                }
            }

            if (update != null) {
                for (indexedEntity in updateEntitiesWithIndexes) {
                    update.use(client update table)
                }
            }

            client.insertAndReturn(*arrayOf(createEntitiesWithIndexes.map {
                if (createByColumnName != null && byUser != null) {
                    it.value.setProperty(createByColumnName, byUser)
                }
                if (createDateTimeColumnName != null) {
                    it.value.setProperty(createDateTimeColumnName, LocalDateTime.now())
                }
                it.value
            })).collect { it.forEachIndexed { index, value -> result[createEntitiesWithIndexes[index].index] = value } }

            return@withContext result.toList()
        }
    }!!

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
        predicate?.use(wheres)

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

        return (limitOffset ?: ordersBy).fetchAll()
    }

    override suspend fun find(properties: List<String>?, predicate: Predicate?, sort: List<Order>?): Flow<T> {

    }

    override suspend fun find(
        page: Page,
        properties: List<String>?,
        predicate: Predicate?,
        sort: List<Order>?
    ): PageResult<T> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(predicate: Predicate?): Long =
        predicate?.use(client deleteFrom table)?.execute() ?: (client deleteAllFrom table)

    suspend fun delete(entity: T): Boolean = delete(field(tableIdColumnName).eq(entityId(entity)!!)) > 0L

    override suspend fun count(predicate: Predicate?): Long =
        predicate?.use(client selectCountFrom table)?.fetchOne() ?: (client selectCountAllFrom table)


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun T.setProperty(name: String, value: Any) =
        this::class.declaredMemberProperties.associateBy { KProperty<*>::name }.find { it.name == name }?.call(this, value)

    private fun Update.use(update: CoroutinesSqlClientDeleteOrUpdate.Update<T>) {

    }

    private fun Predicate.use(wheres: CoroutinesSqlClientSelect.Wheres<List<Any?>>) {

    }

    private fun Predicate.use(modify: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>): CoroutinesSqlClientDeleteOrUpdate.Return {

    }

    private fun <R : Any> Predicate.use(fromTable: CoroutinesSqlClientSelect.FromTable<R, T>): CoroutinesSqlClientSelect.Return<R> {

    }

    private fun String.column(): Column<*, *> =
        table::class.declaredMemberProperties.find { it.name == this }?.let { it.getter.call(table) as Column<*, *> }!!

}