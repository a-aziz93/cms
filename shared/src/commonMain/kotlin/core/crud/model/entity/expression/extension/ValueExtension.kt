package core.crud.model.entity.expression.extension

import core.crud.model.entity.expression.value.*
import java.time.temporal.Temporal

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