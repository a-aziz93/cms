package core.crud.model.entity.expression.variable.extension

import core.crud.model.entity.expression.logic.Logic
import core.crud.model.entity.expression.value.*
import core.crud.model.entity.expression.value.BooleanCollectionValue.Companion.booleanCollection
import core.crud.model.entity.expression.value.BooleanValue.Companion.boolean
import core.crud.model.entity.expression.value.CharCollectionValue.Companion.charCollection
import core.crud.model.entity.expression.value.CharValue.Companion.char
import core.crud.model.entity.expression.value.FieldValue.Companion.field
import core.crud.model.entity.expression.value.NumberCollectionValue.Companion.numberCollection
import core.crud.model.entity.expression.value.NumberValue.Companion.number
import core.crud.model.entity.expression.value.StringCollectionValue.Companion.stringCollection
import core.crud.model.entity.expression.value.StringValue.Companion.string
import core.crud.model.entity.expression.value.TemporalCollectionValue.Companion.temporalCollection
import core.crud.model.entity.expression.value.TemporalValue.Companion.temporal
import core.crud.model.entity.expression.variable.FieldVariable
import java.time.temporal.Temporal

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

fun FieldVariable.eq(value: Any?) = Logic.eq(this, value.v())

fun FieldVariable.neq(value: Any?) = Logic.neq(this, value.v())

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