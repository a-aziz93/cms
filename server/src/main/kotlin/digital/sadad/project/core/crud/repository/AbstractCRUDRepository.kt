package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.entity.Order
import core.crud.model.entity.LimitOffset
import core.crud.model.entity.Update
import core.crud.model.entity.expression.aggregate.AggregateExpression
import core.crud.model.entity.expression.aggregate.AggregateExpression.*
import core.crud.model.entity.expression.logic.Logic
import core.crud.model.entity.expression.projection.Projection
import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.core.crud.model.TableMeta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import kotlin.reflect.full.*

abstract class AbstractCRUDRepository<T : Any, ID : Any>(
    protected val client: R2dbcSqlClient,
    protected val table: Table<T>,
) : CRUD<T, ID> {

    private val tableMeta = TableMeta(table)

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

    override suspend fun update(updates: Collection<Update>): List<Long> = client.transactional {
        withContext(Dispatchers.IO) {
            updates.map {
                (client update table).execute(it)
            }
        }
    }!!

    override suspend fun find(
        sort: Collection<Order>?,
        predicate: Logic?,
        limitOffset: LimitOffset?,
    ): Flow<T> = withContext(Dispatchers.IO) {
        val selectFrom = client.selectFrom(table)

        selectFrom.wheres()
            .execute(sort, predicate, limitOffset)
    }

    override suspend fun find(
        projections: Collection<Projection>,
        sort: Collection<Order>?,
        predicate: Logic?,
        limitOffset: LimitOffset?,
    ): Flow<List<Any?>> = withContext(Dispatchers.IO) {
        val selects = client.selects()
        UserTable.email
        projections.forEach {
            if (it.distinct == true) {
                selects.selectDistinct(it.property.column())
            } else {
                selects.select(it.property.column())
            }
        }

        val froms = selects.froms()
        froms.from(table)

        froms.wheres()
            .execute(sort, predicate, limitOffset)
    }

    override suspend fun delete(predicate: Logic?): Long = withContext(Dispatchers.IO) {
        (client deleteFrom table).predicate(predicate).execute()
    }

    override suspend fun aggregate(
        operation: AggregateExpression,
        projection: Projection?,
        predicate: Logic?,
    ): Number =
        when (operation) {
            COUNT -> (if (projection == null) {
                (client selectCountFrom table).predicate(predicate)
            } else {
                (client selectCount projection.property.column() from table).predicate(predicate)
            }).fetchOne()

            MAX -> client.selectMax(projection!!.property.column() as MinMaxColumn<*, *>).from(table)
                .predicate(predicate).fetchOne() as Number

            MIN -> client.selectMin(projection!!.property.column() as MinMaxColumn<*, *>).from(table)
                .predicate(predicate).fetchOne() as Number

            AVG -> client.selectAvg(projection!!.property.column() as NumericColumn<*, *>).from(table)
                .predicate(predicate).fetchOne()

            SUM -> client.selectSum(projection!!.property.column() as WholeNumberColumn<*, *>).from(table)
                .predicate(predicate).fetchOne()
        }!!

    ///////////////////////////////////////////////////HELPER///////////////////////////////////////////////////


    private fun String.column() = tableMeta.columns[this.lowercase()]!!.column
    private fun String.columnValueGetter() = tableMeta.columns[this.lowercase()]!!.valueGetter
    private fun String.columnValueSetter() = tableMeta.columns[this.lowercase()]!!.valueSetter

    abstract fun onCreate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    abstract fun onUpdate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    private suspend fun CoroutinesSqlClientDeleteOrUpdate.Update<T>.execute(update: Update): Long =
        update.properties.entries.fold(this) { sets, (property, value) ->
            property.columnValueSetter()(sets, value)
        }.predicate(update.predicate).execute()

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.execute(
        sort: Collection<Order>?,
        predicate: Logic?,
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

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.predicate(predicate: Logic?): CoroutinesSqlClientSelect.Wheres<R> {
        this.where(UserTable.email).eq("")
        return this
    }

    private fun CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>.predicate(predicate: Logic?): CoroutinesSqlClientDeleteOrUpdate.Return {
        this.where(UserTable.email).eq("")

        return this
    }

    private fun <R : Any> CoroutinesSqlClientSelect.FromTable<R, T>.predicate(predicate: Logic?): CoroutinesSqlClientSelect.Return<R> {
        this.where(UserTable.email).eq("")
        return this
    }


}