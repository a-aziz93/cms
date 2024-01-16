package core.crud.model.entity.predicate.operation

import kotlinx.serialization.Serializable

@Serializable
abstract class Predicate(
    val operation: PredicateOperation,
    vararg val values: core.crud.model.entity.predicate.PredicateVariable
) : core.crud.model.entity.predicate.PredicateVariable {
    @Suppress("UNUSED")
    fun and(vararg value: core.crud.model.entity.predicate.PredicateVariable) = LogicalPredicate.and(this, *value)

    fun or(vararg value: core.crud.model.entity.predicate.PredicateVariable) = LogicalPredicate.or(this, *value)

    @Suppress("UNUSED")
    fun not() = LogicalPredicate.not(this)

    //TODO
    @Suppress("UNUSED")
    fun <D : Any> convert(converter: (predicate: Predicate) -> D): D {
        val predicates = mutableListOf<Triple<Predicate, Int, D?>>(Triple(this, 0, null))

        while (predicates.size > 0) {

        }

        return predicates.first().third!!
    }
}