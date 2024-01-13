package core.crud.model.predicate.extension

import core.crud.model.predicate.IntPredicateValue.Companion.int
import core.crud.model.predicate.LongPredicateValue.Companion.long
import core.crud.model.predicate.PredicateField.Companion.field
import core.crud.model.predicate.StringPredicateValue.Companion.string

fun Int.v() = int(this)

fun Long.v() = long(this)

fun String.f() = field(this)

fun String.v() = string(this)