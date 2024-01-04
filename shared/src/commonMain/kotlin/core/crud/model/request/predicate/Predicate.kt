package core.crud.model.request.predicate

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
abstract class Predicate<T : Any>(
    val operation: T,
    vararg val values:@Contextual Any
) {
    @Suppress("UNUSED")
    fun and(vararg value: Any) = LogicalPredicate.and(this, value)

    fun or(vararg value: Any) = LogicalPredicate.or(this, value)

    @Suppress("UNUSED")
    fun not() = LogicalPredicate.not(this)

    //TODO
    @Suppress("UNUSED")
    fun <T : Any> convert(converter: (predicate: Predicate<*>) -> T): Any {
        val predicates = mutableListOf<Any>()
        while (predicates.size > 0) {

        }

        return predicates.first()
    }
}