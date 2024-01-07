package core.crud.model.request.predicate

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
abstract class Predicate(
    val operation: @Contextual Any,
    vararg val values: @Contextual Any
) {
    @Suppress("UNUSED")
    fun and(vararg value: Any) = LogicalPredicate.and(this, value)

    fun or(vararg value: Any) = LogicalPredicate.or(this, value)

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