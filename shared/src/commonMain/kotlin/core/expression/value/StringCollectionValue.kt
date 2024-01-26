package core.expression.value

import core.expression.variable.StringCollectionVariable
import kotlinx.serialization.Serializable

@Serializable
open class StringCollectionValue private constructor(override val value: Collection<String>) : CollectionValue<String>,
    StringCollectionVariable {
    companion object {
        fun stringCollection(value: Collection<String>) = StringCollectionValue(value)
    }
}
