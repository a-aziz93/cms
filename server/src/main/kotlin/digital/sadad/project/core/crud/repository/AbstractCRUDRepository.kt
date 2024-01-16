package digital.sadad.project.core.crud.repository

import core.crud.CRUD
import core.crud.model.entity.Order
import core.crud.model.entity.Slice
import core.crud.model.entity.Update
import core.crud.model.entity.aggregate.AggregateOperation
import core.crud.model.entity.aggregate.AggregateOperation.*
import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.projection.Projection
import digital.sadad.project.auth.entity.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.ufoss.kotysa.*
import org.ufoss.kotysa.columns.*
import java.math.BigDecimal
import java.math.BigInteger
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

abstract class AbstractCRUDRepository<T : Any, ID : Any>(
    protected val client: R2dbcSqlClient,
    protected val table: Table<T>,
) : CRUD<T, ID> {

    override suspend fun save(
        entities: List<T>,
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

    override suspend fun update(updates: List<Update>): List<Long> = client.transactional {
        withContext(Dispatchers.IO) {
            updates.map {
                (client update table).execute(it)
            }
        }
    }!!

    override suspend fun find(
        sort: List<Order>?,
        predicate: Predicate?,
        slice: Slice?,
    ): Flow<T> = withContext(Dispatchers.IO) {
        val selectFrom = client.selectFrom(table)

        selectFrom.wheres()
            .execute(sort, predicate, slice)
    }

    override suspend fun find(
        projections: List<Projection>,
        sort: List<Order>?,
        predicate: Predicate?,
        slice: Slice?,
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
            .execute(sort, predicate, slice)
    }

    override suspend fun delete(predicate: Predicate?): Long = withContext(Dispatchers.IO) {
        (client deleteFrom table).predicate(predicate).execute()
    }

    override suspend fun aggregate(
        operation: AggregateOperation,
        projection: Projection?,
        predicate: Predicate?,
    ): Number =
        when (operation) {
            COUNT -> if (projection == null) {
                (client selectCountFrom table).predicate(predicate)
            } else {
                (client selectCount projection.property.column() from table).predicate(predicate)
            }

            MAX -> client.selectMax(projection!!.property.column() as MinMaxColumn<*, Number>).from(table)
                .predicate(predicate)

            MIN -> client.selectMin(projection!!.property.column() as MinMaxColumn<*, Number>).from(table)
                .predicate(predicate)

            AVG -> client.selectAvg(projection!!.property.column() as NumericColumn<*, *>).from(table)
                .predicate(predicate)

            SUM -> client.selectSum(projection!!.property.column() as WholeNumberColumn<*, *>).from(table)
                .predicate(predicate)
        }.fetchOne()!!

    ///////////////////////////////////////////////////HELPER///////////////////////////////////////////////////

    private val columns: Map<String, Column<*, *>> =
        table::class.memberProperties.filter { it.returnType.isSubtypeOf(columnKType) }.associate {
            val column = it.call(table)!!
            (column::class.declaredMembers.find { it.name.lowercase() == "columnname" }?.name
                ?: it.name) to column as Column<*, *>
        }

    private fun String.column() = columns[this]!!

    private val identityColumn = columns.entries.find {
        it.value is UuidDbUuidColumnNotNull<*> ||
                it.value is IntDbIdentityColumnNotNull<*> ||
                it.value is LongDbIdentityColumnNotNull<*>
    }!!

    private var columnSetters: Map<String, (CoroutinesSqlClientDeleteOrUpdate.Update<T>, value: Any?) -> CoroutinesSqlClientDeleteOrUpdate.Update<T>> =
        columns.entries.associate {
            it.key to when (it.value) {
                is BigDecimalColumnNotNull<*> -> { update, value ->
                    update.set(it.value as BigDecimalColumnNotNull<T>).eq(value as BigDecimal)
                }

                is BigDecimalColumnNullable<*> -> { update, value ->
                    update.set(it.value as BigDecimalColumnNullable<T>).eq(value as BigDecimal?)
                }

                is BooleanColumnNotNull<*> -> { update, value ->
                    update.set(it.value as BooleanColumnNotNull<T>).eq(value as Boolean)
                }

                is ByteArrayColumnNotNull -> { update, value ->
                    update.set(it.value as ByteArrayColumnNotNull<T>).eq(value as ByteArray)
                }

                is ByteArrayColumnNullable -> { update, value ->
                    update.set(it.value as ByteArrayColumnNullable<T>).eq(value as ByteArray?)
                }

                is DoubleColumnNotNull -> { update, value ->
                    update.set(it.value as DoubleColumnNotNull<T>).eq(value as Double)
                }

                is DoubleColumnNullable -> { update, value ->
                    update.set(it.value as DoubleColumnNullable<T>).eq(value as Double?)
                }

                is FloatColumnNotNull -> { update, value ->
                    update.set(it.value as FloatColumnNotNull<T>).eq(value as Float)
                }

                is FloatColumnNullable -> { update, value ->
                    update.set(it.value as FloatColumnNullable<T>).eq(value as Float?)
                }

                is IntColumnNotNull -> { update, value -> update.set(it.value as IntColumnNotNull<T>).eq(value as Int) }
                is IntColumnNullable -> { update, value ->
                    update.set(it.value as IntColumnNullable<T>).eq(value as Int?)
                }

                is KotlinxLocalDateColumnNotNull -> { update, value ->
                    update.set(it.value as KotlinxLocalDateColumnNotNull<T>).eq(value as LocalDate)
                }

                is KotlinxLocalDateColumnNullable -> { update, value ->
                    update.set(it.value as KotlinxLocalDateColumnNullable<T>).eq(value as LocalDate?)
                }

                is KotlinxLocalDateTimeColumnNotNull -> { update, value ->
                    update.set(it.value as KotlinxLocalDateTimeColumnNotNull<T>).eq(value as LocalDateTime)
                }

                is KotlinxLocalDateTimeColumnNullable -> { update, value ->
                    update.set(it.value as KotlinxLocalDateTimeColumnNullable<T>).eq(value as LocalDateTime?)
                }

                is KotlinxLocalTimeColumnNotNull -> { update, value ->
                    update.set(it.value as KotlinxLocalTimeColumnNotNull<T>).eq(value as LocalTime)
                }

                is KotlinxLocalTimeColumnNullable -> { update, value ->
                    update.set(it.value as KotlinxLocalTimeColumnNullable<T>).eq(value as LocalTime?)
                }

                is LocalDateColumnNotNull -> { update, value ->
                    update.set(it.value as LocalDateColumnNotNull<T>).eq(value as java.time.LocalDate)
                }

                is LocalDateColumnNullable -> { update, value ->
                    update.set(it.value as LocalDateColumnNullable<T>).eq(value as java.time.LocalDate?)
                }

                is LocalDateTimeColumnNotNull -> { update, value ->
                    update.set(it.value as LocalDateTimeColumnNotNull<T>).eq(value as java.time.LocalDateTime)
                }

                is LocalDateTimeColumnNullable -> { update, value ->
                    update.set(it.value as LocalDateTimeColumnNullable<T>).eq(value as java.time.LocalDateTime?)
                }

                is LocalTimeColumnNotNull -> { update, value ->
                    update.set(it.value as LocalTimeColumnNotNull<T>).eq(value as java.time.LocalTime)
                }

                is LocalTimeColumnNullable -> { update, value ->
                    update.set(it.value as LocalTimeColumnNullable<T>).eq(value as java.time.LocalTime?)
                }

                is LongColumnNotNull -> { update, value ->
                    update.set(it.value as LongColumnNotNull<T>).eq(value as Long)
                }

                is LongColumnNullable -> { update, value ->
                    update.set(it.value as LongColumnNullable<T>).eq(value as Long?)
                }

                is OffsetDateTimeColumnNotNull -> { update, value ->
                    update.set(it.value as OffsetDateTimeColumnNotNull<T>).eq(value as OffsetDateTime)
                }

                is OffsetDateTimeColumnNullable -> { update, value ->
                    update.set(it.value as OffsetDateTimeColumnNullable<T>).eq(value as OffsetDateTime?)
                }

                is StringColumnNotNull -> { update, value ->
                    update.set(it.value as StringColumnNotNull<T>).eq(value as String)
                }

                is StringColumnNullable -> { update, value ->
                    update.set(it.value as StringColumnNullable<T>).eq(value as String?)
                }

                is UuidColumnNotNull -> { update, value ->
                    update.set(it.value as UuidColumnNotNull<T>).eq(value as UUID)
                }

                is UuidColumnNullable -> { update, value ->
                    update.set(it.value as UuidColumnNullable<T>).eq(value as UUID?)
                }

                else -> throw Exception("No setter defined for column ${it.key}")
            }
        }

    private fun String.columnSetter() = columnSetters[this]!!

    abstract fun onCreate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    abstract fun onUpdate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    private suspend fun CoroutinesSqlClientDeleteOrUpdate.Update<T>.execute(update: Update): Long =
        update.properties.entries.fold(this) { sets, (property, value) ->
            property.columnSetter()(sets, value)
        }.predicate(update.predicate).execute()

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.execute(
        sort: List<Order>?,
        predicate: Predicate?,
        slice: Slice?,
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

        var limitOffset: CoroutinesSqlClientSelect.LimitOffset<R>? = null
        if (slice != null) {
            slice.offset()?.let { limitOffset = (ordersBy ?: wheres).offset(it) }
            slice.size?.let { limitOffset = (limitOffset ?: ordersBy ?: wheres).limit(it) }
        }

        return (limitOffset ?: ordersBy ?: wheres).fetchAll()
    }

    private fun <R : Any> CoroutinesSqlClientSelect.Wheres<R>.predicate(predicate: Predicate?): CoroutinesSqlClientSelect.Wheres<R> {

        return this
    }

    private fun CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>.predicate(predicate: Predicate?): CoroutinesSqlClientDeleteOrUpdate.Return {

        return this
    }

    private fun <R : Any> CoroutinesSqlClientSelect.FromTable<R, T>.predicate(predicate: Predicate?): CoroutinesSqlClientSelect.Return<R> {

        return this
    }

    companion object {
        private val columnKType = Column::class.createType()
    }
}