package core.crud.model.entity.expression.value.extension

import core.crud.model.entity.expression.value.*
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

fun FieldValue.eq(value: Any?) = this.eq(value.v())
fun FieldValue.neq(value: Any?) = this.neq(value.v())

fun Boolean.v() = BooleanValue.boolean(this)
fun Collection<Boolean>.v() = BooleanCollectionValue.booleanCollection(this)

fun Char.v() = CharValue.char(this)
fun Collection<Char>.v() = CharCollectionValue.charCollection(this)

fun Number.v() = NumberValue.number(this)
fun Collection<Number>.v() = NumberCollectionValue.numberCollection(this)

fun Temporal.v() = TemporalValue.temporal(this)
fun Collection<Temporal>.v() = TemporalCollectionValue.temporalCollection(this)

fun String.v() = StringValue.string(this)
fun Collection<String>.v() = StringCollectionValue.stringCollection(this)
fun String.f() = FieldValue.field(this)