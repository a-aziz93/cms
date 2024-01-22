package core.crud.model.entity.expression.variable.extension

import core.crud.model.entity.expression.logic.Logic
import core.crud.model.entity.expression.logic.LogicExpression
import core.crud.model.entity.expression.value.*
import core.crud.model.entity.expression.value.BooleanCollectionValue.Companion.booleanCollection
import core.crud.model.entity.expression.value.BooleanValue.Companion.boolean
import core.crud.model.entity.expression.value.CharCollectionValue.Companion.charCollection
import core.crud.model.entity.expression.value.CharValue.Companion.char
import core.crud.model.entity.expression.value.FieldValue.Companion.field
import core.crud.model.entity.expression.value.NullValue.Companion.nul
import core.crud.model.entity.expression.value.NumberCollectionValue.Companion.numberCollection
import core.crud.model.entity.expression.value.NumberValue.Companion.number
import core.crud.model.entity.expression.value.StringCollectionValue.Companion.stringCollection
import core.crud.model.entity.expression.value.StringValue.Companion.string
import core.crud.model.entity.expression.value.TemporalCollectionValue.Companion.temporalCollection
import core.crud.model.entity.expression.value.TemporalValue.Companion.temporal
import core.crud.model.entity.expression.variable.BooleanVariable
import core.crud.model.entity.expression.variable.CollectionVariable
import core.crud.model.entity.expression.variable.StringVariable
import core.crud.model.entity.expression.variable.Variable
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

private fun <T:Any?> T.checkEquality(value: T, equator: (Variable, Variable) -> BooleanVariable): BooleanVariable {
    val leftValue = if (this is FieldValue) this else this.eqV()
    val rightValue = if (value is FieldValue) value else value.eqV()
    if (leftValue::class == rightValue::class || leftValue is FieldValue || leftValue is NullValue || rightValue is FieldValue || rightValue is NullValue) {
        return equator(leftValue, rightValue)
    } else {
        throw IllegalArgumentException("Arguments has different types")
    }
}

fun <T:Any?> T.eq(value: T): BooleanVariable = this.checkEquality(value, Logic::eq)

fun <T:Any?> T.neq(value: T): BooleanVariable = this.checkEquality(value, Logic::neq)

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

fun Any.gt(value: Any): BooleanVariable = this.compare(value, Logic::gt)

fun Any.gte(value: Any): BooleanVariable = this.compare(value, Logic::gte)

fun Any.lt(value: Any): BooleanVariable = this.compare(value, Logic::lt)

fun Any.lte(value: Any): BooleanVariable = this.compare(value, Logic::lte)

fun Any.between(leftValue: Any, rightValue: Any): BooleanVariable {
    val values = listOf(
        if (this is FieldValue) this else this.compV(),
        if (leftValue is FieldValue) leftValue else this.compV(),
        if (rightValue is FieldValue) rightValue else this.compV()
    )
    val nonFieldValues = values.filter { it !is FieldValue }
    if (nonFieldValues.isEmpty() || nonFieldValues.drop(1).all { it::class == nonFieldValues.first() }) {
        return Logic.between(values[0], values[1], values[2])
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
        is FieldValue ->

    }

    10.v().eq(nul())
}

fun Any.`in`(value: Any): BooleanVariable = checkIsIn(value, Logic::`in`)

fun Any.nin(value: Any): BooleanVariable = checkIsIn(value, Logic::nin)

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

fun String.f() = field(this)