package core.crud.model.predicate.value

import core.crud.model.predicate.PredicateValue
import core.crud.model.predicate.operation.ComparePredicate
import kotlinx.serialization.Serializable

@Serializable
abstract class AbstractPredicateValue<T : Any> : PredicateValue {
    abstract val value: T

    fun eq(value: PredicateValue) = ComparePredicate.eq(this, value)

    @Suppress("UNUSED")
    fun neq(value: PredicateValue) = ComparePredicate.neq(this, value)

    @Suppress("UNUSED")
    fun gt(value: PredicateValue) = ComparePredicate.gt(this, value)

    @Suppress("UNUSED")
    fun gte(value: PredicateValue) = ComparePredicate.gte(this, value)

    @Suppress("UNUSED")
    fun lt(value: PredicateValue) = ComparePredicate.lt(this, value)

    @Suppress("UNUSED")
    fun lte(value: PredicateValue) = ComparePredicate.lte(this, value)

    @Suppress("UNUSED")
    fun btw(leftValue: PredicateValue, rightValue: PredicateValue) = ComparePredicate.btw(leftValue, this, rightValue)

    @Suppress("UNUSED")
    fun like(value: PredicateValue) = ComparePredicate.like(this, value)

    @Suppress("UNUSED")
    fun include(value: PredicateValue) = ComparePredicate.like(this, value)
}