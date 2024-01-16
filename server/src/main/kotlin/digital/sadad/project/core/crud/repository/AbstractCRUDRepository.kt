package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.entity.Order
import core.crud.model.entity.Update
import core.crud.model.predicate.operation.Predicate
import core.crud.model.entity.predicate.extension.f
import core.crud.model.entity.predicate.extension.v
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import java.time.LocalDateTime
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

abstract class AbstractCRUDRepository<T : Any, ID : Any>(
    protected val client: R2dbcSqlClient,
    protected val table: Table<T>,
) : CRUD<T, ID> {

    private val identityDbColumn: Pair<String, AbstractDbColumn<T, ID>> = table::class.memberProperties.firstNotNullOf {
        val value = when (it.returnType) {
            uuidIdentityColumnKType -> (it.call(table) as UuidDbUuidColumnNotNull<T>) as AbstractDbColumn<T, ID>
            intIdentityColumnKType -> (it.call(table) as IntDbIdentityColumnNotNull<T>) as AbstractDbColumn<T, ID>
            longIdentityColumnKType -> (it.call(table) as LongDbIdentityColumnNotNull<T>) as AbstractDbColumn<T, ID>
            else -> null
        }
        if (value == null) {
            null
        } else {
            it.name to value
        }
    }

    private val dbColumns: Map<String, AbstractDbColumn<T, *>> =
        table::class.memberProperties.filter { it.returnType.isSubtypeOf(columnKType) }.associate {
            it.name to it.call(table) as AbstractDbColumn<T, *>
        }

    abstract fun onCreate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    abstract fun onUpdate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    override suspend fun save(
        entities: List<T>,
        updateIfExists: Boolean,
        byUser: String?
    ): List<T> = client.transactional {
        withContext(Dispatchers.IO) {


            val createOrUpdateEntities = entities.map {
                val id = identityDbColumn.second.entityGetter(it)
                if (id == null || find(predicate = identityDbColumn.first.f().eq(id.v())).firstOrNull() == null) {
                    true to onCreate(it, byUser, LocalDateTime.now())
                } else {
                    false to it
                }
            }

            createOrUpdateEntities.partition { it.first }.let { (createEntities, updateEntities) ->
                if (updateIfExists) {
                    update(updateEntities.map { updateEntity ->
                        Update(
                            dbColumns.entries.associate {
                                it.key to it.value.entityGetter(updateEntity.second)
                            },
                            identityDbColumn.first.f()
                                .eq(identityDbColumn.second.entityGetter(updateEntity.second)!!.v())
                        )
                    })
                }

                client.insertAndReturn(*arrayOf(createEntities.map {
                    it.second
                }))
            }

            createOrUpdateEntities.map { it.second }
        }
    }!!

    override suspend fun update(updates: List<Update>): List<Long> = client.transactional {
        withContext(Dispatchers.IO) {
            updates.map {
                it.use(client update table).execute()
            }
        }
    }!!

    override suspend fun find(
        predicate: Predicate?,
        sort: List<Order>?,
        offset: Long?,
        limit: Long?,
    ): Flow<T> = withContext(Dispatchers.IO) {
        val selectFrom = client.selectFrom(table)

        val wheres = selectFrom.wheres()
        predicate?.use(wheres)

        val ordersBy = wheres.ordersBy()
        sort?.forEach {
            if (it.ascending) {
                ordersBy.orderByAsc(it.name.dbColumn())
            } else {
                ordersBy.orderByDesc(it.name.dbColumn())
            }
        }

        var limitOffset: CoroutinesSqlClientSelect.LimitOffset<T>? = null
        if (offset != null) {
            limitOffset = ordersBy.offset(offset)
        }
        if (limit != null) {
            limitOffset = (limitOffset ?: ordersBy).limit(limit)
        }

        (limitOffset ?: ordersBy).fetchAll()
    }

    override suspend fun find(
        properties: List<String>,
        predicate: Predicate?,
        sort: List<Order>?,
        offset: Long?,
        limit: Long?,
    ): Flow<List<Any?>> = withContext(Dispatchers.IO) {
        val selects = client.selects()
        properties.forEach {
            selects.select(it.dbColumn())
        }

        val froms = selects.froms()
        froms.from(table)

        val wheres = froms.wheres()
        predicate?.use(wheres)

        val ordersBy = wheres.ordersBy()
        sort?.forEach {
            if (it.ascending) {
                ordersBy.orderByAsc(it.name.dbColumn())
            } else {
                ordersBy.orderByDesc(it.name.dbColumn())
            }
        }

        var limitOffset: CoroutinesSqlClientSelect.LimitOffset<List<Any?>>? = null
        if (offset != null) {
            limitOffset = ordersBy.offset(offset)
        }
        if (limit != null) {
            limitOffset = (limitOffset ?: ordersBy).limit(limit)
        }

        (limitOffset ?: ordersBy).fetchAll()
    }

    override suspend fun delete(predicate: Predicate?): Long = withContext(Dispatchers.IO) {
        predicate?.use(client deleteFrom table)?.execute() ?: (client deleteAllFrom table)
    }

    override suspend fun count(predicate: Predicate?): Long = withContext(Dispatchers.IO) {
        predicate?.use(client selectCountFrom table)?.fetchOne() ?: (client selectCountAllFrom table)
    }


    ///////////////////////////////////////////////////HELPER///////////////////////////////////////////////////

    private fun Update.use(update: CoroutinesSqlClientDeleteOrUpdate.Update<T>): CoroutinesSqlClientDeleteOrUpdate.Return {

    }

    private fun Predicate.use(wheres: CoroutinesSqlClientSelect.Wheres<*>) {

    }

    private fun Predicate.use(modify: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>): CoroutinesSqlClientDeleteOrUpdate.Return {

    }

    private fun <R : Any> Predicate.use(fromTable: CoroutinesSqlClientSelect.FromTable<R, T>): CoroutinesSqlClientSelect.Return<R> {

    }

    private fun String.dbColumn(): AbstractDbColumn<T, *> = dbColumns[this]!!

    companion object {
        private val columnKType = Column::class.createType()
        private val uuidIdentityColumnKType = UuidDbUuidColumnNotNull::class.createType()
        private val intIdentityColumnKType = IntDbIdentityColumnNotNull::class.createType()
        private val longIdentityColumnKType = LongDbIdentityColumnNotNull::class.createType()
    }
}