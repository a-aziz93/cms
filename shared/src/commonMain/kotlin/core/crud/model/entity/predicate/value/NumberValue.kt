package core.crud.model.entity.predicate.value

import core.crud.model.entity.predicate.NumberVariable
import core.crud.model.entity.predicate.operation.Predicate
import core.crud.model.entity.predicate.operation.PredicateOperation
import kotlinx.serialization.Serializable

@Serializable
class NumberValue<T : Number>(override val value: T) : Value<T>, NumberVariable {
    companion object {
        fun <T : Number> number(value: T) = NumberValue(value)
    }
}
