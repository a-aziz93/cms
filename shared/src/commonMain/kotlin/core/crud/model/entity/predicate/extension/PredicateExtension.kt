package core.crud.model.entity.predicate.extension

import core.crud.model.entity.predicate.*
import core.crud.model.entity.predicate.value.*
import core.crud.model.entity.predicate.value.BooleanCollectionValue.Companion.booleanCollection
import core.crud.model.entity.predicate.value.BooleanValue.Companion.boolean
import core.crud.model.entity.predicate.value.CharCollectionValue.Companion.charCollection
import core.crud.model.entity.predicate.value.CharValue.Companion.char
import core.crud.model.entity.predicate.value.Field.Companion.field
import core.crud.model.entity.predicate.value.NumberCollectionValue.Companion.numberCollection
import core.crud.model.entity.predicate.value.NumberValue.Companion.number
import core.crud.model.entity.predicate.value.StringCollectionValue.Companion.stringCollection
import core.crud.model.entity.predicate.value.StringValue.Companion.string
import core.crud.model.entity.predicate.value.TemporalCollectionValue.Companion.temporalCollection
import core.crud.model.entity.predicate.value.TemporalValue.Companion.temporal
import java.time.temporal.Temporal

// VALUE
private fun Any?.v(): Value<*> =
    if (this == null) NullValue.nul()
    else
        when (this) {
            is Boolean -> this.v()
            is Char -> this.v()
            is Number -> this.v()
            is Temporal -> this.v()
            is String -> this.v()
            else -> throw IllegalAccessException("Not predicate value")
        }

fun Field.eq(value: Any?) = this.eq(value.v())
fun Field.neq(value: Any?) = this.eq(value.v())

// BOOLEAN
fun Boolean.v() = boolean(this)
fun Collection<Boolean>.v() = booleanCollection(this)
fun Boolean.eq(value: BooleanVariable) =
    this.v().eq(value)

fun Boolean.neq(value: BooleanVariable) =
    this.v().neq(value)

fun Boolean.and(vararg values: BooleanVariable) = this.v().and(*values)

fun Boolean.or(vararg values: BooleanVariable) = this.v().or(*values)

fun Boolean.xor(vararg values: BooleanVariable) = this.v().xor(*values)

fun Boolean.no() = this.v().no()

fun Boolean.eq(value: Boolean) = this.eq(value.v())

fun Boolean.neq(value: Boolean) = this.neq(value.v())

fun Boolean.and(vararg values: Boolean) = this.and(*values.map { it.v() }.toTypedArray())

fun Boolean.or(vararg values: Boolean) = this.or(*values.map { it.v() }.toTypedArray())

fun Boolean.xor(vararg values: Boolean) = this.xor(*values.map { it.v() }.toTypedArray())

// CHAR
fun Char.v() = char(this)
fun Collection<Char>.v() = charCollection(this)

fun Char.eq(value: CharVariable) =
    this.v().eq(value)

fun Char.neq(value: CharVariable) =
    this.v().neq(value)

fun Char.ln(value: CharCollectionValue) =
    this.v().ln(value)

fun Char.nin(value: CharCollectionValue) =
    this.v().nin(value)

fun Char.eq(value: Char) =
    this.eq(value.v())

fun Char.neq(value: Char) =
    this.neq(value.v())

fun Char.ln(value: Collection<Char>) =
    this.ln(value.v())

fun Char.nin(value: Collection<Char>) =
    this.nin(value.v())

// NUMBER
fun Number.v() = number(this)
fun Collection<Number>.v() = numberCollection(this)

fun Number.eq(value: NumberVariable) =
    this.v().eq(value)

fun Number.neq(value: NumberVariable) =
    this.v().neq(value)

fun Number.gt(value: NumberVariable) =
    this.v().gt(value)

fun Number.gte(value: NumberVariable) =
    this.v().gte(value)

fun Number.lt(value: NumberVariable) =
    this.v().lt(value)

fun Number.lte(value: NumberVariable) =
    this.v().lte(value)

fun Number.between(leftValue: NumberVariable, rightValue: NumberVariable) =
    this.v().between(leftValue, rightValue)

fun Number.ln(value: NumberCollectionValue<*>) =
    this.v().ln(value)

fun Number.nin(value: NumberCollectionValue<*>) =
    this.v().nin(value)

fun Number.eq(value: Number) =
    this.eq(value.v())

fun Number.neq(value: Number) =
    this.neq(value.v())

fun Number.gt(value: Number) =
    this.gt(value.v())

fun Number.gte(value: Number) =
    this.gte(value.v())

fun Number.lt(value: Number) =
    this.lt(value.v())

fun Number.lte(value: Number) =
    this.lte(value.v())

fun Number.between(leftValue: Number, rightValue: Number) =
    this.between(leftValue.v(), rightValue.v())

fun Number.ln(value: Collection<Number>) =
    this.ln(value.v())

fun Number.nin(value: Collection<Number>) =
    this.nin(value.v())

// TEMPORAL
fun Temporal.v() = temporal(this)
fun Collection<Temporal>.v() = temporalCollection(this)

fun Temporal.eq(value: TemporalVariable) =
    this.v().eq(value)

fun Temporal.neq(value: TemporalVariable) =
    this.v().neq(value)

fun Temporal.gt(value: TemporalVariable) =
    this.v().gt(value)

fun Temporal.gte(value: TemporalVariable) =
    this.v().gte(value)

fun Temporal.lt(value: TemporalVariable) =
    this.v().lt(value)

fun Temporal.lte(value: TemporalVariable) =
    this.v().lte(value)

fun Temporal.between(leftValue: TemporalVariable, rightValue: TemporalVariable) =
    this.v().between(leftValue, rightValue)

fun Temporal.ln(value: TemporalCollectionValue<*>) =
    this.v().ln(value)

fun Temporal.nin(value: TemporalCollectionValue<*>) =
    this.v().nin(value)

fun Temporal.eq(value: Temporal) =
    this.eq(value.v())

fun Temporal.neq(value: Temporal) =
    this.neq(value.v())

fun Temporal.gt(value: Temporal) =
    this.gt(value.v())

fun Temporal.gte(value: Temporal) =
    this.gte(value.v())

fun Temporal.lt(value: Temporal) =
    this.lt(value.v())

fun Temporal.lte(value: Temporal) =
    this.lte(value.v())

fun Temporal.between(leftValue: Temporal, rightValue: Temporal) =
    this.between(leftValue.v(), rightValue.v())

fun Temporal.ln(value: Collection<Temporal>) =
    this.ln(value.v())

fun Temporal.nin(value: Collection<Temporal>) =
    this.nin(value.v())

// STRING
fun String.v() = string(this)
fun Collection<String>.v() = stringCollection(this)
fun String.f() = field(this)

fun String.eq(value: StringVariable) =
    this.v().eq(value)

fun String.neq(value: StringVariable) =
    this.v().neq(value)

fun String.like(value: StringVariable) =
    this.v().like(value)

fun String.ln(value: StringCollectionValue) =
    this.v().ln(value)

fun String.nin(value: StringCollectionValue) =
    this.v().nin(value)

fun String.eq(value: String) =
    this.eq(value.v())

fun String.neq(value: String) =
    this.neq(value.v())

fun String.like(value: String) =
    this.like(value.v())

fun String.ln(value: Collection<String>) =
    this.ln(value.v())

fun String.nin(value: Collection<String>) =
    this.nin(value.v())