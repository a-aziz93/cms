package core.expression.value

import core.crud.repository.model.expression.variable.*
import kotlinx.serialization.Serializable

@Serializable
open class FieldValue(override val value: String) : Value<String>,
    BooleanVariable, NumberVariable, StringVariable, TemporalVariable, CollectionVariable {

    companion object {
        fun field(name: String) = FieldValue(name)
    }
}