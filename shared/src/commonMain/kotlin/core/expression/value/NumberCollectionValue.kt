package core.expression.value

import core.expression.variable.NumberCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
class NumberCollectionValue<T : Number> private constructor(override val value: Collection<T>) : CollectionValue<T>,
    NumberCollectionVariable {
    companion object {
        fun <T : Number> numberCollection(value: Collection<T>) = NumberCollectionValue(value)
    }
}