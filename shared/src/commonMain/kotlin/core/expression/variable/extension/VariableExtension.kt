package core.expression.variable.extension

import core.expression.value.BooleanCollectionValue.Companion.booleanCollection
import core.expression.value.BooleanValue.Companion.boolean
import core.expression.value.CharCollectionValue.Companion.charCollection
import core.expression.value.CharValue.Companion.char
import core.expression.value.FieldValue.Companion.field
import core.expression.value.NumberCollectionValue.Companion.numberCollection
import core.expression.value.NumberValue.Companion.number
import core.expression.value.StringCollectionValue.Companion.stringCollection
import core.expression.value.StringValue.Companion.string
import core.expression.value.TemporalCollectionValue.Companion.temporalCollection
import core.expression.value.TemporalValue.Companion.temporal
import java.time.temporal.Temporal

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