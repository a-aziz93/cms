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
import digital.sadad.project.core.crud.model.ColumnInteraction
import digital.sadad.project.core.crud.model.ColumnMetadata
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
import kotlin.reflect.full.*

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

    private val columnsMetadata: Map<String, ColumnMetadata<T>> =
        table::class.declaredMemberProperties.filter { it.returnType.isSubtypeOf(columnKType) }.associate {
            val column = it.call(table)!!
            val columnName = (column::class.memberProperties.find { it.name.lowercase() == "columnname" }?.name
                ?: it.name).lowercase()
            columnName to ColumnMetadata(
                column as Column<T, *>,
                column is UuidDbUuidColumnNotNull<*> ||
                        column is IntDbIdentityColumnNotNull<*> ||
                        column is LongDbIdentityColumnNotNull<*>,
                (column as AbstractDbColumn<T, *>).entityGetter,
                when (column) {
                    is BigDecimalColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as BigDecimalColumnNotNull<T>).eq(value as BigDecimal)
                        },
                    )

                    is BigDecimalColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as BigDecimalColumnNullable<T>).eq(value as BigDecimal?)
                        },
                    )

                    is BooleanColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as BooleanColumnNotNull<T>).eq(value as Boolean)
                        },
                    )

                    is ByteArrayColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as ByteArrayColumnNotNull<T>).eq(value as ByteArray)
                        },
                    )

                    is ByteArrayColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as ByteArrayColumnNullable<T>).eq(value as ByteArray?)
                        },
                    )

                    is DoubleColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as DoubleColumnNotNull<T>).eq(value as Double)
                        },
                    )

                    is DoubleColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as DoubleColumnNullable<T>).eq(value as Double?)
                        },
                    )

                    is FloatColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as FloatColumnNotNull<T>).eq(value as Float)
                        },
                    )

                    is FloatColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as FloatColumnNullable<T>).eq(value as Float?)
                        },
                    )

                    is IntColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as IntColumnNotNull<T>).eq(value as Int)
                        },
                    )

                    is IntColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as IntColumnNullable<T>).eq(value as Int?)
                        },
                    )

                    is KotlinxLocalDateColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalDateColumnNotNull<T>).eq(value as LocalDate)
                        },
                    )

                    is KotlinxLocalDateColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalDateColumnNullable<T>).eq(value as LocalDate?)
                        },
                    )

                    is KotlinxLocalDateTimeColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalDateTimeColumnNotNull<T>).eq(value as LocalDateTime)
                        },
                    )

                    is KotlinxLocalDateTimeColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalDateTimeColumnNullable<T>).eq(value as LocalDateTime?)
                        },
                    )

                    is KotlinxLocalTimeColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalTimeColumnNotNull<T>).eq(value as LocalTime)
                        },
                    )

                    is KotlinxLocalTimeColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as KotlinxLocalTimeColumnNullable<T>).eq(value as LocalTime?)
                        },
                    )

                    is LocalDateColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalDateColumnNotNull<T>).eq(value as java.time.LocalDate)
                        },
                    )

                    is LocalDateColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalDateColumnNullable<T>).eq(value as java.time.LocalDate?)
                        },
                    )

                    is LocalDateTimeColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalDateTimeColumnNotNull<T>).eq(value as java.time.LocalDateTime)
                        },
                    )

                    is LocalDateTimeColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalDateTimeColumnNullable<T>).eq(value as java.time.LocalDateTime?)
                        },
                    )

                    is LocalTimeColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalTimeColumnNotNull<T>).eq(value as java.time.LocalTime)
                        },
                    )

                    is LocalTimeColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LocalTimeColumnNullable<T>).eq(value as java.time.LocalTime?)
                        },
                    )

                    is LongColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LongColumnNotNull<T>).eq(value as Long)
                        },
                    )

                    is LongColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as LongColumnNullable<T>).eq(value as Long?)
                        },
                    )

                    is OffsetDateTimeColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as OffsetDateTimeColumnNotNull<T>).eq(value as OffsetDateTime)
                        },
                    )

                    is OffsetDateTimeColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as OffsetDateTimeColumnNullable<T>).eq(value as OffsetDateTime?)
                        },
                    )

                    is StringColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as StringColumnNotNull<T>).eq(value as String)
                        },
                    )

                    is StringColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as StringColumnNullable<T>).eq(value as String?)
                        },
                    )

                    is UuidColumnNotNull<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as UuidColumnNotNull<T>).eq(value as UUID)
                        },
                    )

                    is UuidColumnNullable<*> -> ColumnInteraction(
                        { update, value ->
                            update.set(column as UuidColumnNullable<T>).eq(value as UUID?)
                        },
                    )

                    else -> throw Exception("No setter defined for column ${columnName}")
                }
            )
        }

    private fun String.column() = columnsMetadata[this.lowercase()]!!.column

    private val identityColumn = columnsMetadata.entries.find { it.value.isIdentity }!!

    private fun String.interaction() = columnsMetadata[this.lowercase()]!!.interaction

    abstract fun onCreate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    abstract fun onUpdate(entity: T, byUser: String?, dateTime: LocalDateTime): T

    private suspend fun CoroutinesSqlClientDeleteOrUpdate.Update<T>.execute(update: Update): Long =
        update.properties.entries.fold(this) { sets, (property, value) ->
            property.interaction().updateSetter(sets, value)
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
        this.where(UserTable.email).eq("")
        return this
    }

    private fun CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<T>.predicate(predicate: Predicate?): CoroutinesSqlClientDeleteOrUpdate.Return {
        this.where(UserTable.email).eq("")

        return this
    }

    private fun <R : Any> CoroutinesSqlClientSelect.FromTable<R, T>.predicate(predicate: Predicate?): CoroutinesSqlClientSelect.Return<R> {
        this.where(UserTable.email).eq("")
        return this
    }

    companion object {
        private val columnKType = Column::class.createType()
    }
}