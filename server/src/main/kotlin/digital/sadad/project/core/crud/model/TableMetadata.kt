package digital.sadad.project.core.crud.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import org.ufoss.kotysa.*
import org.ufoss.kotysa.Table
import org.ufoss.kotysa.columns.AbstractDbColumn
import org.ufoss.kotysa.columns.IntDbIdentityColumnNotNull
import org.ufoss.kotysa.columns.LongDbIdentityColumnNotNull
import org.ufoss.kotysa.columns.UuidDbUuidColumnNotNull
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties

class TableMetadata<T : Any>(table: Table<T>) {
    val columnsMetadatas: Map<String, ColumnMetadata<T>> =
        table::class.declaredMemberProperties.filter { it.returnType.isSubtypeOf(columnKType) }
            .associate {
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
                        is BigDecimalColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as BigDecimalColumnNotNull<T>).eq(value as BigDecimal)
                            }
                       

                        is BigDecimalColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as BigDecimalColumnNullable<T>).eq(value as BigDecimal?)
                            }
                       

                        is BooleanColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as BooleanColumnNotNull<T>).eq(value as Boolean)
                            }
                       

                        is ByteArrayColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as ByteArrayColumnNotNull<T>).eq(value as ByteArray)
                            }
                       

                        is ByteArrayColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as ByteArrayColumnNullable<T>).eq(value as ByteArray?)
                            }
                       

                        is DoubleColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as DoubleColumnNotNull<T>).eq(value as Double)
                            }
                       

                        is DoubleColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as DoubleColumnNullable<T>).eq(value as Double?)
                            }
                       

                        is FloatColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as FloatColumnNotNull<T>).eq(value as Float)
                            }
                       

                        is FloatColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as FloatColumnNullable<T>).eq(value as Float?)
                            }
                       

                        is IntColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as IntColumnNotNull<T>).eq(value as Int)
                            }
                       

                        is IntColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as IntColumnNullable<T>).eq(value as Int?)
                            }
                       

                        is KotlinxLocalDateColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalDateColumnNotNull<T>).eq(value as LocalDate)
                            }
                       

                        is KotlinxLocalDateColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalDateColumnNullable<T>).eq(value as LocalDate?)
                            }
                       

                        is KotlinxLocalDateTimeColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalDateTimeColumnNotNull<T>).eq(value as LocalDateTime)
                            }
                       

                        is KotlinxLocalDateTimeColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalDateTimeColumnNullable<T>).eq(value as LocalDateTime?)
                            }
                       

                        is KotlinxLocalTimeColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalTimeColumnNotNull<T>).eq(value as LocalTime)
                            }
                       

                        is KotlinxLocalTimeColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as KotlinxLocalTimeColumnNullable<T>).eq(value as LocalTime?)
                            }
                       

                        is LocalDateColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as LocalDateColumnNotNull<T>).eq(value as java.time.LocalDate)
                            }
                       

                        is LocalDateColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as LocalDateColumnNullable<T>).eq(value as java.time.LocalDate?)
                            }
                       

                        is LocalDateTimeColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as LocalDateTimeColumnNotNull<T>).eq(value as java.time.LocalDateTime)
                            }
                       

                        is LocalDateTimeColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as LocalDateTimeColumnNullable<T>)
                                    .eq(value as java.time.LocalDateTime?)
                            }
                       

                        is LocalTimeColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as LocalTimeColumnNotNull<T>).eq(value as java.time.LocalTime)
                            }
                       

                        is LocalTimeColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as LocalTimeColumnNullable<T>).eq(value as java.time.LocalTime?)
                            }
                       

                        is LongColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as LongColumnNotNull<T>).eq(value as Long)
                            }
                       

                        is LongColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as LongColumnNullable<T>).eq(value as Long?)
                            }
                       

                        is OffsetDateTimeColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as OffsetDateTimeColumnNotNull<T>).eq(value as OffsetDateTime)
                            }
                       

                        is OffsetDateTimeColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as OffsetDateTimeColumnNullable<T>).eq(value as OffsetDateTime?)
                            }
                       

                        is StringColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as StringColumnNotNull<T>).eq(value as String)
                            }
                       

                        is StringColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as StringColumnNullable<T>).eq(value as String?)
                            }
                       

                        is UuidColumnNotNull<*> -> 
                            { update, value ->
                                update.set(column as UuidColumnNotNull<T>).eq(value as UUID)
                            }
                       

                        is UuidColumnNullable<*> -> 
                            { update, value ->
                                update.set(column as UuidColumnNullable<T>).eq(value as UUID?)
                            }
                        else -> throw Exception("No setter defined for column $columnName")
                    }
                )
               
            }

    val identityColumnMetadata = columnsMetadatas.entries.find { it.value.isIdentity }!!

    companion object {
        private val columnKType = ColumnMetadata::class.createType()
    }

}