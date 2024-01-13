package core.crud.model.predicate.extension

import core.crud.model.predicate.value.IntPredicateValue.Companion.int
import core.crud.model.predicate.value.LongPredicateValue.Companion.long
import core.crud.model.predicate.value.PredicateField.Companion.field
import core.crud.model.predicate.value.StringPredicateValue.Companion.string

fun Int.v() = int(this)

fun Long.v() = long(this)

fun String.f() = field(this)

fun String.v() = string(this)