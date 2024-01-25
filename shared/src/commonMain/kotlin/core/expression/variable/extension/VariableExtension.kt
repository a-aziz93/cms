package core.expression.variable.extension

import core.expression.logic.LogicExpression
import core.expression.value.BooleanCollectionValue.Companion.booleanCollection
import core.expression.value.BooleanValue.Companion.boolean
import core.expression.value.CharCollectionValue.Companion.charCollection
import core.expression.value.CharValue.Companion.char
import core.expression.value.FieldValue
import core.expression.value.FieldValue.Companion.field
import core.expression.value.NullValue
import core.expression.value.NumberCollectionValue.Companion.numberCollection
import core.expression.value.NumberValue.Companion.number
import core.expression.value.StringCollectionValue.Companion.stringCollection
import core.expression.value.StringValue.Companion.string
import core.expression.value.TemporalCollectionValue.Companion.temporalCollection
import core.expression.value.TemporalValue.Companion.temporal
import core.expression.value.Value
import core.expression.variable.BooleanVariable
import core.expression.variable.CollectionVariable
import core.expression.variable.Variable
import java.time.temporal.Temporal

private fun Any?.eqV(): Value<*> =
    if (this == null) NullValue.nul()
    else
        when (this) {
            is Boolean -> this.v()
            is Char -> this.v()
            is Number -> this.v()
            is Temporal -> this.v()
            is String -> this.v()
            else -> throw IllegalAccessException("Not equatable value")
        }

private fun <T : Any?> T.checkEquality(value: T, equator: (Variable, Variable) -> BooleanVariable): BooleanVariable {
    val leftValue = if (this is FieldValue) this else this.eqV()
    val rightValue = if (value is FieldValue) value else value.eqV()
    if (leftValue::class == rightValue::class || leftValue is FieldValue || leftValue is NullValue || rightValue is FieldValue || rightValue is NullValue) {
        return equator(leftValue, rightValue)
    } else {
        throw IllegalArgumentException("Arguments has different types")
    }
}

fun <T : Any?> T.eq(value: T): BooleanVariable = this.checkEquality(value, LogicExpression::eq)

fun <T : Any?> T.neq(value: T): BooleanVariable = this.checkEquality(value, LogicExpression::neq)

private fun Any.compV(): Value<*> =
    when (this) {
        is Number -> this.v()
        is Temporal -> this.v()
        else -> throw IllegalAccessException("Not comparable value")
    }

private fun Any.compare(value: Any, comparator: (Variable, Variable) -> BooleanVariable): BooleanVariable {
    val leftValue = if (this is FieldValue) this else this.compV()
    val rightValue = if (value is FieldValue) value else value.compV()
    if (leftValue::class == rightValue::class || leftValue is FieldValue || rightValue is FieldValue) {
        return comparator(leftValue, rightValue)
    } else {
        throw IllegalArgumentException("Arguments has different types")
    }
}

fun <T : Any> T.gt(value: T): BooleanVariable = this.compare(value, LogicExpression::gt)

fun <T : Any?> T.gte(value: T): BooleanVariable = this.compare(value, LogicExpression::gte)

fun <T : Any?> T.lt(value: T): BooleanVariable = this.compare(value, LogicExpression::lt)

fun <T : Any?> T.lte(value: T): BooleanVariable = this.compare(value, LogicExpression::lte)

fun <T : Any?> T.between(leftValue: T, rightValue: T): BooleanVariable {
    val values = listOf(
        if (this is FieldValue) this else this.compV(),
        if (leftValue is FieldValue) leftValue else this.compV(),
        if (rightValue is FieldValue) rightValue else this.compV()
    )
    val nonFieldValues = values.filter { it !is FieldValue }
    if (nonFieldValues.isEmpty() || nonFieldValues.drop(1).all { it::class == nonFieldValues.first() }) {
        return LogicExpression.between(values[0], values[1], values[2])
    } else {
        throw IllegalArgumentException("Arguments has different types")
    }
}

private fun Any.inV(): Value<*> =
    when (this) {
        is Char -> this.v()
        is Number -> this.v()
        is Temporal -> this.v()
        is String -> this.v()
        else -> throw IllegalAccessException("Not inable value")
    }

fun Any.checkIsIn(value: Any, equator: (Variable, CollectionVariable) -> BooleanVariable): BooleanVariable {
    val leftValue = if (this is FieldValue) this else this.eqV()
    when (leftValue) {


    }
}

//fun Any.`in`(value: Any): BooleanVariable = checkIsIn(value, Logic::`in`)
//
//fun Any.nin(value: Any): BooleanVariable = checkIsIn(value, Logic::nin)

fun Boolean.v() = boolean(this)

fun Collection<Boolean>.v() = booleanCollection(this)

fun Char.v() = char(this)

fun Collection<Char>.v() = charCollection(this)

fun Number.v() = number(this)

fun Collection<Number>.v() = numberCollection(this)

fun Temporal.v() = temporal(this)

fun Collection<Temporal>.v() = temporalCollection(this)

fun String.v() = string(this)

fun Collection<String>.v() = stringCollection(this)

fun String.f() = field(listOf(this))
fun Collection<String>.f() = field(this)