package core.crud.model.entity.expression.predicate.value

import core.crud.model.entity.expression.predicate.NumberVariable
import kotlinx.serialization.Serializable

@Serializable
class NumberValue<T : Number>(override val value: T) : Value<T>,
    core.crud.model.entity.expression.predicate.NumberVariable {
    companion object {
        fun <T : Number> number(value: T) = NumberValue(value)
    }
}
