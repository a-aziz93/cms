package digital.sadad.project.core.crud.repository

import core.crud.repository.CRUDRepository
import core.crud.repository.model.io.Order
import core.crud.repository.model.io.LimitOffset
import core.crud.repository.model.transaction.UpdateTransaction
import core.crud.repository.model.expression.variable.BooleanVariable
import core.crud.repository.model.expression.variable.Variable
import core.expression.aggregate.AggregateExpression.*
import core.crud.repository.model.expression.projection.Projection
import digital.sadad.project.core.crud.model.TableMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import kotlin.reflect.full.*

abstract class KotysaCRUDRepository<T : Any, ID : Any>(
    protected val client: R2dbcSqlClient,
    protected val table: Table<T>,
) : CRUDRepository<T> {

    private val tableMetadata = TableMetadata(table)

    override suspend fun transactional(byUser: String?, block: suspend CRUDRepository<T>.() -> Any?) {
        client.transactional {
            block()
        }
    }

    override suspend fun save(
        entities: Collection<T>,
        updateIfExists: Boolean,
        byUser: String?
    ): List<T> = client.transactional {
        withContext(Dispatchers.IO) {


            val createOrUpdateEntities = entities.map {
                val id = identityColumn.second.entityGetter(it)
                if (id == null || find(predicate = identityDbColumn.first.f().eq(id.v())).firstOrNull() == null) {
                    true to onCreate(it, byUser, LocalDateTime.now())
                } else {
                    false to it
                }
            }

            createOrUpdateEntities.partition { it.first }.let { (createEntities, updateEntities) ->
                if (updateIfExists) {
                    update(updateEntities.map { updateEntity ->
                        UpdateTransaction(
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

    override suspend fun update(updates: Collection<UpdateTransaction>): List<Long> = client.transactional {
        withContext(Dispatchers.IO) {
            updates.map {
                (client update table).execute(it)
            }
        }
    }!!

    override suspend fun find(
        sort: Collection<Order>?,
        predicate: BooleanVariable?,
        limitOffset: LimitOffset?,
    ): Flow<T> = withContext(Dispatchers.IO) {
        val selectFrom = client.selectFrom(table)

        selectFrom.wheres()
            .execute(sort, predicate, limitOffset)
    }

    override suspend fun find(
        projections: Collection<Variable>,
        sort: Collection<Order>?,
        predicate: BooleanVariable?,
        limitOffset: LimitOffset?,
    ): Flow<List<Any?>> = withContext(Dispatchers.IO) {
        val selects = client.selects()
        UserTable.email
        projections.filterIsInstance<Projection>().forEach {
            if (it.distinct == true) {
                selects.selectDistinct(it.value.column())
            } else {
                selects.select(it.value.column())
            }
        }

        val froms = selects.froms()
        froms.from(table)

        froms.wheres()
            .execute(sort, predicate, limitOffset)
    }

    override suspend fun delete(predicate: BooleanVariable?): Long = withContext(Dispatchers.IO) {
        (client deleteFrom table).predicate(predicate).execute()
    }

    override suspend fun aggregate(
        aggregate: core.expression.aggregate.Aggregate,
        predicate: BooleanVariable?,
    ): Number =
        when (aggregate.operation) {
            COUNT -> (if (aggregate.projection == null) {
                (client selectCountFrom table).predicate(predicate)
            } else {
                (client selectCount aggregate.projection!!.value.column() from table).predicate(predicate)
            }).fetchOne()

            MAX -> client.selectMax(aggregate.projection!!.value.column() as MinMaxColumn<*, *>).from(table)
                .predicate(predicate).fetchOne() as Number

            MIN -> client.selectMin(aggregate.projection!!.value.column() as MinMaxColumn<*, *>).from(table)
                .predicate(predicate).fetchOne() as Number

            AVG -> client.selectAvg(aggregate.projection!!.value.column() as NumericColumn<*, *>).from(table)
                .predicate(predicate).fetchOne()

            SUM -> client.selectSum(aggregate.projection!!.value.column() as WholeNumberColumn<*, *>).from(table)
                .predicate(predicate).fetchOne()
        }!!

    ///////////////////////////////////////////////////HELPER///////////////////////////////////////////////////


    private fun String.column() = tableMetadata.columnsMetadatas[this.lowercase()]!!.column
    private fun String.columnValueGetter() = tableMetadata.columnsMetadatas[this.lowercase()]!!.valueGetter
    private fun String.columnValueSetter() = tableMetadata.columnsMetadatas[this.lowercase()]!!.valueSetter

    abstract fun onCreate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    abstract fun onUpdate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    private suspend fun CoroutinesSqlClientDeleteOrUpdate.Update<T>.execute(update: UpdateTransaction): Long =
        update.properties.entries.fold(this) { sets, (property, value) ->
            property.columnValueSetter()(sets, value)
        }.predicate(update.predicate)
            .execute()

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.execute(
        sort: Collection<Order>?,
        predicate: BooleanVariable?,
        limitOffset: LimitOffset?,
    ): Flow<R> {
        val wheres = this.predicate(predicate)

        var ordersBy: CoroutinesSqlClientSelect.OrdersBy<R>? = null
        if (sort != null) {
            ordersBy = wheres.ordersBy()
            sort.forEach {
                if (it.ascending) {
                    ordersBy.orderByAsc(it.name.column())
                } else {
                    ordersBy.orderByDesc(it.name.column())
                }
            }
        }

        var lo: CoroutinesSqlClientSelect.LimitOffset<R>? = null
        if (limitOffset != null) {
            limitOffset.offset?.let { lo = (ordersBy ?: wheres).offset(it) }
            limitOffset.limit?.let { lo = (lo ?: ordersBy ?: wheres).limit(it) }
        }

        return (lo ?: ordersBy ?: wheres).fetchAll()
    }

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.predicate(predicate: BooleanVariable?): CoroutinesSqlClientSelect.Wheres<R> {
        this.where(UserTable.email).eq("")
        return this
    }

    private fun CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>.predicate(predicate: BooleanVariable?): CoroutinesSqlClientDeleteOrUpdate.Return {
        this.where(UserTable.email).eq("")

        return this
    }

    private fun <R : Any> CoroutinesSqlClientSelect.FromTable<R, T>.predicate(predicate: BooleanVariable?): CoroutinesSqlClientSelect.Return<R> {
        this.where(UserTable.email).eq("")
        return this
    }


}