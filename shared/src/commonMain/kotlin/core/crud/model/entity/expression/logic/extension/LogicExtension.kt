package core.crud.model.entity.expression.logic.extension

import core.crud.model.entity.expression.*
import core.crud.model.entity.expression.value.extension.v
import core.crud.model.entity.expression.value.*
import java.time.temporal.Temporal

// BOOLEAN
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
fun Char.eq(value: CharVariable) =
    this.v().eq(value)

fun Char.neq(value: CharVariable) =
    this.v().neq(value)

fun Char.`in`(value: CharCollectionVariable) =
    this.v().`in`(value)

fun Char.nin(value: CharCollectionVariable) =
    this.v().nin(value)

fun Char.eq(value: Char) =
    this.eq(value.v())

fun Char.neq(value: Char) =
    this.neq(value.v())

fun Char.`in`(value: Collection<Char>) =
    this.`in`(value.v())

fun Char.nin(value: Collection<Char>) =
    this.nin(value.v())

// NUMBER
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

fun Number.`in`(value: NumberCollectionVariable) =
    this.v().`in`(value)

fun Number.nin(value: NumberCollectionVariable) =
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

fun Number.`in`(value: Collection<Number>) =
    this.`in`(value.v())

fun Number.nin(value: Collection<Number>) =
    this.nin(value.v())

// TEMPORAL
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

fun Temporal.`in`(value: TemporalCollectionVariable) =
    this.v().`in`(value)

fun Temporal.nin(value: TemporalCollectionVariable) =
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

fun Temporal.`in`(value: Collection<Temporal>) =
    this.`in`(value.v())

fun Temporal.nin(value: Collection<Temporal>) =
    this.nin(value.v())

// STRING
fun String.eq(value: StringVariable) =
    this.v().eq(value)

fun String.neq(value: StringVariable) =
    this.v().neq(value)

fun String.like(value: StringVariable) =
    this.v().like(value)

fun String.`in`(value: StringCollectionVariable) =
    this.v().`in`(value)

fun String.nin(value: StringCollectionVariable) =
    this.v().nin(value)

fun String.eq(value: String) =
    this.eq(value.v())

fun String.neq(value: String) =
    this.neq(value.v())

fun String.like(value: String) =
    this.like(value.v())

fun String.`in`(value: Collection<String>) =
    this.`in`(value.v())

fun String.nin(value: Collection<String>) =
    this.nin(value.v())