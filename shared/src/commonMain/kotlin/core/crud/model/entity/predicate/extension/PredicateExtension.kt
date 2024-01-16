package core.crud.model.entity.predicate.extension

import core.crud.model.entity.predicate.operation.LogicalPredicate
import core.crud.model.entity.predicate.value.BigDecimalPredicateValue.Companion.bigDecimal
import core.crud.model.entity.predicate.value.BigIntegerPredicateValue.Companion.bigInteger
import core.crud.model.entity.predicate.value.BooleanPredicateValue.Companion.boolean
import core.crud.model.entity.predicate.value.BytePredicateValue.Companion.byte
import core.crud.model.entity.predicate.value.CharPredicateValue.Companion.char
import core.crud.model.entity.predicate.value.DoublePredicateValue.Companion.double
import core.crud.model.entity.predicate.value.FloatPredicateValue.Companion.float
import core.crud.model.entity.predicate.value.IntPredicateValue.Companion.int
import core.crud.model.entity.predicate.value.LongPredicateValue.Companion.long
import core.crud.model.entity.predicate.value.NullPredicateValue.Companion.nul
import core.crud.model.entity.predicate.value.PredicateField.Companion.field
import core.crud.model.entity.predicate.value.PredicateValue
import core.crud.model.entity.predicate.value.ShortPredicateValue.Companion.short
import core.crud.model.entity.predicate.value.StringPredicateValue.Companion.string
import java.math.BigDecimal
import java.math.BigInteger

fun Boolean.eq(value: Boolean) = this.v().eq(value.v())
fun Boolean.neq(value: Boolean) = this.v().eq(value.v())
fun Boolean.eq(value: LogicalPredicate) = this.v().eq(value)
fun Boolean.neq(value: LogicalPredicate) = this.v().eq(value)

fun String.eq(value: String) = this.v().eq(value.v())
fun String.neq(str: String) = this.v().neq(str.v())

fun String.f() = field(this)

private fun Any?.v(): PredicateValue<*> =
    if (this == null) nul()
    else
        when (this) {
            is Boolean -> boolean(this as Boolean)
            is Char -> char(this)
            is Byte -> byte(this)
            is Short -> short(this)
            is Int -> int(this)
            is Long -> long(this)
            is Float -> float(this)
            is Double -> double(this)
            is String -> string(this)
            is BigInteger -> bigInteger(this)
            is BigDecimal -> bigDecimal(this)
            else -> throw IllegalAccessException("Not predicate object")
        }