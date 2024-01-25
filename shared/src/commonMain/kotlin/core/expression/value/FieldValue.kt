package core.expression.value

import core.expression.variable.*
import kotlinx.serialization.Serializable

@Serializable
open class FieldValue(override val value: Collection<String>) : Value<Collection<String>>,
    BooleanVariable, NumberVariable, StringVariable, TemporalVariable, CollectionVariable {

    companion object {
        fun field(value: Collection<String>) = FieldValue(value)
    }
}