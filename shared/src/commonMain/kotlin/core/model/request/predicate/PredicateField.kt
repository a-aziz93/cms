package core.model.request.predicate

import kotlinx.serialization.Serializable


@Serializable
class PredicateField private constructor(val name: String) {
    fun eq(value: Any) = ComparePredicate.eq(this, value)

    @Suppress("UNUSED")
    fun neq(value: Any) = ComparePredicate.neq(this, value)

    @Suppress("UNUSED")
    fun gt(value: Any) = ComparePredicate.gt(this, value)

    @Suppress("UNUSED")
    fun gte(value: Any) = ComparePredicate.gte(this, value)

    @Suppress("UNUSED")
    fun lt(value: Any) = ComparePredicate.lt(this, value)

    @Suppress("UNUSED")
    fun lte(value: Any) = ComparePredicate.lte(this, value)

    @Suppress("UNUSED")
    fun btw(leftValue: Any, rightValue: Any) = ComparePredicate.btw(leftValue, this, rightValue)

    @Suppress("UNUSED")
    fun like(value: Any) = ComparePredicate.like(this, value)

    companion object {
        fun field(name: String) = PredicateField(name)
    }
}