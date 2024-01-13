package core.crud.model.predicate.extension

import core.crud.model.predicate.value.BooleanArrayPredicateValue.Companion.booleanArray
import core.crud.model.predicate.value.BooleanPredicateValue.Companion.boolean
import core.crud.model.predicate.value.CharPredicateValue.Companion.char
import core.crud.model.predicate.value.ByteArrayPredicateValue.Companion.byteArray
import core.crud.model.predicate.value.BytePredicateValue.Companion.byte
import core.crud.model.predicate.value.CharArrayPredicateValue.Companion.charArray
import core.crud.model.predicate.value.ShortPredicateValue.Companion.short
import core.crud.model.predicate.value.ShortArrayPredicateValue.Companion.shortArray
import core.crud.model.predicate.value.IntPredicateValue.Companion.int
import core.crud.model.predicate.value.IntArrayPredicateValue.Companion.intArray
import core.crud.model.predicate.value.LongPredicateValue.Companion.long
import core.crud.model.predicate.value.LongArrayPredicateValue.Companion.longArray
import core.crud.model.predicate.value.FloatPredicateValue.Companion.float
import core.crud.model.predicate.value.FloatArrayPredicateValue.Companion.floatArray
import core.crud.model.predicate.value.DoublePredicateValue.Companion.double
import core.crud.model.predicate.value.DoubleArrayPredicateValue.Companion.doubleArray
import core.crud.model.predicate.value.PredicateField.Companion.field
import core.crud.model.predicate.value.StringPredicateValue.Companion.string

fun Boolean.v() = boolean(this)
fun BooleanArray.v() = booleanArray(this)
fun Char.v() = char(this)
fun CharArray.v() = charArray(this)
fun Byte.v() = byte(this)
fun ByteArray.v() = byteArray(this)
fun Short.v() = short(this)
fun ShortArray.v() = shortArray(this)
fun Int.v() = int(this)
fun IntArray.v() = intArray(this)
fun Long.v() = long(this)
fun LongArray.v() = longArray(this)
fun Float.v() = float(this)
fun FloatArray.v() = floatArray(this)
fun Double.v() = double(this)
fun DoubleArray.v() = doubleArray(this)
fun String.f() = field(this)
fun String.v() = string(this)