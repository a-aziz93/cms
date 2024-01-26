package core.expression.value

import core.expression.variable.CharCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
class CharCollectionValue private constructor(override val value: Collection<Char>) : CollectionValue<Char>,
    CharCollectionVariable {
    companion object {
        fun charCollection(value: Collection<Char>) = CharCollectionValue(value)
    }
}
